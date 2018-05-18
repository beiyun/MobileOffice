package com.beiyun.workers.utils;

import com.beiyun.workers.R;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.Address;
import com.beiyun.workers.entity.PersonInfoQueryParams;
import com.beiyun.workers.entity.TGBasicInfoEntity;
import com.beiyun.workers.entity.User;
import com.beiyun.workers.okhttp.OkHttpUtils;
import com.beiyun.workers.okhttp.callback.BaseInfo;
import com.beiyun.workers.okhttp.callback.CallBack;
import com.beiyun.workers.okhttp.callback.ResponseTCallBack;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Apps;
import com.sdsmdg.tastytoast.TastyToast;
import com.beiyun.library.util.Sps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Response;

/**
 * 数据请求类
 * @author 北云
 */
public class AppRequests {

    private static String EMPTY_DATA = Apps.getString(R.string.noData);
    private static int SUCCESS_CODE = 100;

    public interface CallBackListener<T>{
        /**
         * 请求成功
         * @param data 数据
         */
        void success(List<T> data,int totalSize);

        /**
         * 请求失败
         * @param e 异常
         */
        void failed(IOException e);
    }


    /**
     * 查询人员信息
     * 参数
     * bo.types 类型 1-4  bo.province 所属省   bo.city 所属市
     * bo.county 所属县   bo.uid  烟站  bo.village 村委会
     * bo.villageGroup 村小组   user.type 登录人类型 1-4
     * user.province 登录人所属省   user.city 登录人所属市
     * user.county 登录人所属县   user.uid  登录人烟站
     */
    public static <T> void getPersonInfo(int page, int type, final CallBackListener<T> listener){

        HashMap<String,String> params = new HashMap<>();
        params.put("bo.page", String.valueOf(page));
        params.put("bo.types", String.valueOf(type));

        setLocalParams(params);


        Logs.e("AppRequests getPersonInfo params >" + params);


        OkHttpUtils.postQuery(AppUrl.get().PERSON_INFO, params, new ResponseTCallBack<BaseInfo<ArrayList<TGBasicInfoEntity>>>() {
            @Override
            public void onFailure(IOException e) {
                Logs.e("AppRequests getPersonInfo >"+e.getMessage());
                if(listener != null){
                    listener.failed(e);
                }
            }

            @Override
            protected void onSuccess(BaseInfo<ArrayList<TGBasicInfoEntity>> data) {

                Logs.e("AppRequests getPersonInfo success data >"+data);

                if(data.getResultCode()!= SUCCESS_CODE){
                    toastFailed(data.getReason());
                    onFailure(new IOException(data.getReason()));
                    return;
                }


                ArrayList<TGBasicInfoEntity> list = data.getData().getList();
                if(list == null || list.size() == 0){
                    toastFailed(EMPTY_DATA);
                    return;
                }

                int totalSize = data.getData().total;

                if(listener != null){
                    listener.success((List<T>) list,totalSize);
                }

            }
        });
    }

    /**
     * bo.types 类型 1-8  bo.year 年度  bo.province 所属省
     * bo.city 所属市   bo.county 所属县   bo.uid  烟站  bo.village 村委会
     * bo.villageGroup 村小组  bo.name 姓名  user.type 登录人类型 1-4  user.province 登录人所属省
     * user.city 登录人所属市   user.county 登录人所属县   user.uid  登录人烟站
     * @param <T>
     */
    public static <T> void  getPlantInfo(int type, int year,int page, ResponseTCallBack<T> callBack){
        HashMap<String,String> params = new HashMap<>();
        setLocalParams(params);
        params.put("bo.types", String.valueOf(type));
        params.put("bo.year",String.valueOf(year));
        params.put("bo.page",String.valueOf(page));
        OkHttpUtils.postQuery(AppUrl.get().PLANT_INFO, params,callBack);
    }


    /**
     * 消息接口
     * @param page
     * @param callBack
     * @param <T>
     */
    public static <T> void getMessageInfo(int page,ResponseTCallBack<T> callBack){
        HashMap<String,String> params = new HashMap<>();
        User use = (User) Sps.get(User.class);
        int type = use.getType();
        params.put("bo.page", String.valueOf(page));
        params.put("bo.type", String.valueOf(type));
        params.put("bo.noticeStatus", String.valueOf(2));
        OkHttpUtils.postQuery(AppUrl.get().MESSAGE,params,callBack);
    }

    private static void setLocalParams(HashMap<String, String> params) {
        User user = (User) Sps.get(User.class);
        params.put("user.type", String.valueOf(user.getType()));
        params.put("user.province",user.getProvince());
        params.put("user.city",user.getCity());
        params.put("user.county",user.getCounty());
        params.put("user.uid",user.getUid());
        params.put("user.id",user.getInstructorId());

        try{
            HashMap<String, Address> addressMap = AddressSelector.getAddressMap();
            params.put("bo.province",addressMap.get("key0")==null? "" : addressMap.get("key0").getCode());
            params.put("bo.city",addressMap.get("key1") == null? "" : addressMap.get("key1").getCode());
            params.put("bo.county",addressMap.get("key2") == null? "" : addressMap.get("key2").getCode());
            params.put("bo.uid",addressMap.get("key3") == null? "" : addressMap.get("key3").getCode());
            params.put("bo.village",addressMap.get("key4") == null? "" : addressMap.get("key4").getName());
            params.put("bo.villageGroup",addressMap.get("key5") == null? "" : addressMap.get("key5").getName());
        }catch (Exception e){
            Logs.e("AppRequests getPersonInfo setAddressMap"+e.getMessage());
        }
    }


    private static void toastSuccess(String msg){
        TastyToast.makeText(Apps.getContext(),msg,TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
    }

    private static void toastFailed(String msg){
        TastyToast.makeText(Apps.getContext(),msg,TastyToast.LENGTH_SHORT,TastyToast.ERROR).show();
    }

}
