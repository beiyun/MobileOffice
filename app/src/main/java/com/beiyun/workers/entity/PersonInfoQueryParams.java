package com.beiyun.workers.entity;

public class PersonInfoQueryParams {

    //查询人员信息
    /**
     *  参数
     * bo.types 类型 1-4  bo.province 所属省   bo.city 所属市
     * bo.county 所属县   bo.uid  烟站  bo.village 村委会
     * bo.villageGroup 村小组   user.type 登录人类型 1-4
     * user.province 登录人所属省   user.city 登录人所属市
     * user.county 登录人所属县   user.uid  登录人烟站
     */

    private int page;

    private int types;
    private String province;
    private String city;
    private String county;
    private String uid;
    private String village;
    private String villageGroup;

    private int userType;
    private String userProvince;
    private String userCity;
    private String userCounty;
    private String userUid;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getVillageGroup() {
        return villageGroup;
    }

    public void setVillageGroup(String villageGroup) {
        this.villageGroup = villageGroup;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserCounty() {
        return userCounty;
    }

    public void setUserCounty(String userCounty) {
        this.userCounty = userCounty;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
}
