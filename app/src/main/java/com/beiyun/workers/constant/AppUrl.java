package com.beiyun.workers.constant;

import com.beiyun.library.base.App;

public class AppUrl {

    static String PUBLIC_IP = "http://220.165.15.41:8081/";
    static String PRIVATE_IP = "http://sdmc.free.ngrok.cc/";
    static String LOCAL_IP = "http://192.168.0.103:8080/";

    public String BASE_URL;
    public String BASE_IMAGE_URL;

    public String UPDATE_PASSWORD_STEP_1;

    public String UPDATE_PASSWORD_STEP_2;

    public String LOGIN_SIGN_IN;

    public String SIGN_OUT;

    public String NEWS;


    public String BANNER;


    public String ADDRESS;


    //查询人员信息
    /**
     *  参数
     * bo.types 类型 1-4  bo.province 所属省   bo.city 所属市
     * bo.county 所属县   bo.uid  烟站  bo.village 村委会
     * bo.villageGroup 村小组   user.type 登录人类型 1-4
     * user.province 登录人所属省   user.city 登录人所属市
     * user.county 登录人所属县   user.uid  登录人烟站
     */
    public String PERSON_INFO;


    /**
     * bo.types 类型 1-8  bo.year 年度  bo.province 所属省   bo.city 所属市
     * bo.county 所属县   bo.uid  烟站  bo.village 村委会   bo.villageGroup 村小组
     * bo.name 姓名  user.type 登录人类型 1-4  user.province 登录人所属省   user.city 登录人所属市
     * user.county 登录人所属县   user.uid  登录人烟站
     */
    public String PLANT_INFO;

    public static AppUrl get(){
       return userPublicNet();
    }

    public static AppUrl userPublicNet(){
        return new AppUrl(true);
    }

    public static AppUrl usePrivateNet(){
        return new AppUrl(false);
    }

    public AppUrl(boolean publicNet) {
        if(publicNet){
            BASE_URL = PUBLIC_IP+"/tobacco/";
            BASE_IMAGE_URL = PUBLIC_IP+"imageweb/";
        }else{
            BASE_URL = PRIVATE_IP+"/tobacco/";
            BASE_IMAGE_URL = PRIVATE_IP+"imageweb/";
        }

         UPDATE_PASSWORD_STEP_1 = BASE_URL + "login/login_sendverificationcode.do";
         UPDATE_PASSWORD_STEP_2 = BASE_URL + "login/login_resetpassword.do";
         LOGIN_SIGN_IN = BASE_URL + "login/login_insLogin.do";
         SIGN_OUT = BASE_URL + "login/login_insQuit.do";
         NEWS = BASE_URL + "news/news_query.do";
         BANNER = BASE_URL + "query/query_advertisementList.do";
         ADDRESS = BASE_URL + "login/login_queryPara.do";
         PERSON_INFO = BASE_URL + "login/login_query.do";
         PLANT_INFO = BASE_URL + "plantingQuery/plantingQuery_query.do";

    }



}
