package com.beiyun.workers.utils;

import android.text.TextUtils;

import com.beiyun.workers.R;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.Address;
import com.beiyun.workers.entity.LocalVideoEntity;
import com.beiyun.workers.entity.PersonInfoQueryParams;
import com.beiyun.workers.entity.TGBasicInfoEntity;
import com.beiyun.workers.entity.User;
import com.beiyun.workers.entity.WorkNameSearchEntity;
import com.beiyun.workers.entity.WorkUploadEntity;
import com.beiyun.workers.okhttp.OkHttpManager;
import com.beiyun.workers.okhttp.OkHttpUtils;
import com.beiyun.workers.okhttp.callback.BaseInfo;
import com.beiyun.workers.okhttp.callback.CallBack;
import com.beiyun.workers.okhttp.callback.RequestCallBack;
import com.beiyun.workers.okhttp.callback.ResponseTCallBack;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Apps;
import com.beiyun.workers.okhttp.callback.StringCallBack;
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
    public static <T> void getPersonInfo(int page, int type,String name, final CallBackListener<T> listener){

        HashMap<String,String> params = new HashMap<>();
        params.put("bo.page", String.valueOf(page));
        params.put("bo.types", String.valueOf(type));
        params.put("bo.name", TextUtils.isEmpty(name)?"":name);

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
    public static <T> void  getPlantInfo(int type, int year,String name,int page, StringCallBack callBack){
        HashMap<String,String> params = new HashMap<>();
        setLocalParams(params);
        params.put("bo.types", type == -1?"":String.valueOf(type));
        params.put("bo.year",String.valueOf(year));
        params.put("bo.page",String.valueOf(page));
        params.put("bo.name", TextUtils.isEmpty(name)?"":name);
        OkHttpUtils.postQuery(AppUrl.get().PLANT_INFO, params,callBack);
    }


    /**
     * 查询公示信息
     * @param type
     * @param year
     * @param page
     * @param callBack
     * @param <T>
     *
     *     bo.page 分页 bo.year 年度  bo.province 省 bo.city 市  bo.county 县  bo.uid 烟站
     *     bo.village 村委会 bo.villageGroup 村小组 bo.types 公示类型 1资格审查、2合同预签、3清塘点株、4合同变更

     */
    public static <T> void getPublicInfo(int type, int year,int page,ResponseTCallBack<T> callBack){
        HashMap<String,String> params = new HashMap<>();
        setLocalParams(params);
        params.put("bo.types", type == -1?"":String.valueOf(type));
        params.put("bo.year",String.valueOf(year));
        params.put("bo.page",String.valueOf(page));
        OkHttpUtils.postQuery(AppUrl.get().PUBLIC_INFO, params,callBack);
    }


    /**
     * 查基础设施
     * @param oneType
     * @param twoType
     * @param usedType
     * @param year
     * @param page
     * @param callBack
     * @param <T>
     */
    public static <T> void getBaseStationInfo(int oneType,int twoType,int usedType,String year,int page,ResponseTCallBack<T> callBack){
        HashMap<String,String> params = new HashMap<>();
        setLocalParams1(params);
        params.put("bo.oneType", oneType == -1?"":String.valueOf(oneType));
        params.put("bo.secondType", twoType == -1?"":String.valueOf(twoType));
        params.put("bo.beUsed", usedType == -1?"":String.valueOf(usedType));
        params.put("bo.year",year);
        params.put("bo.page",String.valueOf(page));
        User user = (User) Sps.get(User.class);
        params.put("bo.userId",user.getInstructorId()== null?"":user.getInstructorId());
        OkHttpUtils.postQuery(AppUrl.get().BASIC_STATION_INFO,params,callBack);
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


    /**
     * 视频接口
     * @param page
     * @param callBack
     * @param <T>
     */
    public static <T> void getVideoInfo(int page,ResponseTCallBack<T> callBack){
        HashMap<String,String> params = new HashMap<>();
        User use = (User) Sps.get(User.class);
        int type = use.getType();
        params.put("bo.page", String.valueOf(page));
        params.put("bo.courseStatus", String.valueOf(2));
        OkHttpUtils.postQuery(AppUrl.get().VIDEO,params,callBack);
    }


    /**
     * 视频接口
     * @param page
     * @param callBack
     * @param <T>
     */
    public static <T> void getVideoInfo2(int page,ResponseTCallBack<T> callBack){
        HashMap<String,String> params = new HashMap<>();
        User use = (User) Sps.get(User.class);
        int type = use.getType();
        params.put("bo.page", String.valueOf(page));
        params.put("bo.courseStatus", String.valueOf(2));
        params.put("bo.editId",use.getInstructorId()== null?"":use.getInstructorId());
        OkHttpUtils.postQuery(AppUrl.get().VIDEO,params,callBack);
    }


    /**
     * 视频上传
     * @param entity
     * @param callBack
     * @param <T>
     */
    public static <T> void uploadVideo(LocalVideoEntity entity, RequestCallBack callBack){
        HashMap<String,String> params = new HashMap<>();
        params.put("bo.title",entity.getTitle() == null ? "" : entity.getTitle());
        params.put("bo.introduction",entity.getIntroduction() == null? "" : entity.getIntroduction());
        params.put("bo.content",entity.getContent() == null? "":entity.getContent());
        params.put("bo.editId",entity.getEditId() == null? "" :entity.getEditId());
        params.put("courseModel",entity.getCourseModel()==null?"":entity.getCourseModel());
        OkHttpManager.post().url(AppUrl.get().VIDEO_UPLOAD).params(params).addFile(entity.getVideoFile(),entity.getFileName(),"files")
                .build().execute(callBack);

    }


    /**
     * 工作任务下达
     * @param entity
     * @param callBack
     */
    public static void workSend(WorkUploadEntity entity,RequestCallBack callBack){
        HashMap<String,String> params = new HashMap<>();
        params.put("bo.title",entity.getTitle()== null?"":entity.getTitle());
        params.put("bo.degree",entity.getDegree()== null?"":entity.getDegree());
        params.put("bo.endTime",entity.getEndTime()== null?"":entity.getEndTime());
        params.put("bo.performRole",entity.getPerformRole()== null?"":entity.getPerformRole());
        params.put("bo.demand",entity.getDemand()== null?"":entity.getDemand());
        User user = (User) Sps.get(User.class);
        params.put("bo.assigner", String.valueOf(user.getType()));
        params.put("bo.editId",user.getInstructorId()== null?"":user.getInstructorId());
        OkHttpUtils.postUpload(AppUrl.get().WORK_UPLOAD,params,callBack);
    }


    /**
     * 工作任务名称查询
     * @param endTime
     * @param responseTCallBack
     * @param <T>
     */
    public static <T> void workNameSearch(String endTime,ResponseTCallBack<T> responseTCallBack){
        HashMap<String,String> params = new HashMap<>();
        User user = (User) Sps.get(User.class);
        params.put("bo.editId",user.getInstructorId()== null?"":user.getInstructorId());
        params.put("bo.endTime",endTime== null?"":endTime);
        OkHttpUtils.postQuery(AppUrl.get().WORK_QUERY1,params,responseTCallBack);
    }


    /**
     * 工作任务完成情况查询
     * @param page
     * @param entity
     * @param callBack
     * @param <T>
     */
    public static <T> void workSearch(int page, WorkNameSearchEntity entity,ResponseTCallBack<T> callBack){
        HashMap<String,String> params = new HashMap<>();
        params.put("bo.page", String.valueOf(page));
        params.put("bo.id",entity.getId()== null?"":entity.getId());
        params.put("bo.title",entity.getTitle()== null?"":entity.getTitle());
        params.put("bo.performRole",entity.getPerformRole()== null?"":entity.getPerformRole());
        OkHttpUtils.postQuery(AppUrl.get().WORK_QUERY2,params,callBack);

    }


    /**
     * 意见反馈
     * @param content
     * @param callBack
     */
    public static void report(String content,RequestCallBack callBack){
        HashMap<String,String> params = new HashMap<>();
        User user = (User) Sps.get(User.class);
        params.put("bo.types", "职工APP");
        params.put("bo.linkTel",user.getTel()== null?"":user.getTel());
        params.put("bo.userId",user.getInstructorId()== null?"":user.getInstructorId());
        params.put("bo.userName",user.getNickname()== null?"":user.getNickname());
        params.put("bo.identity",user.getIdentity()== null?"":user.getIdentity());
        params.put("bo.backContent",content);
        OkHttpUtils.postUpload(AppUrl.get().REPORT,params,callBack);
    }


    private static void setLocalParams(HashMap<String, String> params) {
        User user = (User) Sps.get(User.class);
        params.put("user.type", String.valueOf(user.getType()));
        params.put("user.province",user.getProvince()== null?"":user.getProvince());
        params.put("user.city",user.getCity()== null?"":user.getCity());
        params.put("user.county",user.getCounty()== null?"":user.getCounty());
        params.put("user.uid",user.getUid()== null?"":user.getUid());
        params.put("user.id",user.getInstructorId()== null?"":user.getInstructorId());

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


    private static void setLocalParams1(HashMap<String, String> params) {
        User user = (User) Sps.get(User.class);
        params.put("user.type", String.valueOf(user.getType()));
        params.put("user.province",user.getProvince()== null?"":user.getProvince());
        params.put("user.city",user.getCity()== null?"":user.getCity());
        params.put("user.county",user.getCounty()== null?"":user.getCounty());
        params.put("user.uid",user.getUid()== null?"":user.getUid());
        params.put("user.id",user.getInstructorId()== null?"":user.getInstructorId());

        try{
            HashMap<String, Address> addressMap = AddressSelector.getAddressMap();
            params.put("bo.provinceCode",addressMap.get("key0")==null? "" : addressMap.get("key0").getName());
            params.put("bo.cityCode",addressMap.get("key1") == null? "" : addressMap.get("key1").getName());
            params.put("bo.countyCode",addressMap.get("key2") == null? "" : addressMap.get("key2").getName());
            params.put("bo.belongId",addressMap.get("key3") == null? "" : addressMap.get("key3").getCode());
            params.put("bo.villageCode",addressMap.get("key4") == null? "" : addressMap.get("key4").getName());
            params.put("bo.groupCode",addressMap.get("key5") == null? "" : addressMap.get("key5").getName());
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
