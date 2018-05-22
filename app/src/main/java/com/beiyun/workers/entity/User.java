package com.beiyun.workers.entity;

import com.beiyun.library.util.Sps;
import com.beiyun.workers.utils.AppRequests;

import java.util.List;

public class User {

    private String userName;
    private String password;
    private long signInTime;
    private String nickname;
    private String userNumber;
    private String instructorId;
    /**
     * 单位名称ID
     */
    private String uid;
    /**
     *  所属省
     */
    private String province;
    /**
     * 所属市
     */
    private String city;
    /**
     * 所属县
     */
    private String county;

    /**
     * 职工类别
     * 1代表烟站站长  2代表烟站职工  3代表辅导员
     * 41代表省领导  42代表省职工  43代表市领导
     * 44代表市职工  45代表县领导  46代表县职工
     */
    private int type;

    private String identity; // 身份证号

    private String tel; // 联系电话


    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdentity() {
        return identity;
    }

    public String getTel() {
        return tel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(long signInTime) {

        this.signInTime = signInTime;
    }

    @Override
    public String toString() {

        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", signInTime=" + signInTime +
                ", nickname='" + nickname + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", instructorId='" + instructorId + '\'' +
                ", uid='" + uid + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", type=" + type +
                '}';


    }


    /**
     *
     * @param type 1表示公网 2表示专网
     */
    public static void setNetType(int type){
        Sps.init().name("User").build().putInt("netType",type);
    }


    /**
     *
     * @return 1表示公网 2表示专网  默认为公网
     */
    public static int getNetType(){
        int type = Sps.init().name("User").build().getInt("netType");
        return type == -1? 1:type;
    }



}
