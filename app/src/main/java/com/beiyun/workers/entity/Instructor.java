package com.beiyun.workers.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 烟草员工管理表
 * 
 */
public class Instructor extends PersonInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id; // ID

    private String userName; // 用户名

    private String userNumber; // 用户账号

    private String nickname; // 职工姓名

    private String password; // 用户密码

    private int type; // 职工类别      1代表烟站站长  2代表烟站职工  3代表辅导员  41代表省领导  42代表省职工  43代表市领导  44代表市职工  45代表县领导  46代表县职工

    private String identity; // 身份证号

    private String tel; // 联系电话

    private String pid; // 省市县id,type=6 省id type =7 市id type =8 县id

    private String uid; // 单位名称ID

    private String uname; // 单位名称

    private String wid; // 挂钩职工

    private String villageGroup; // 下属村组

    private String address; // 家庭住址

    private String remark; // 备注

    private int available; // 状态   1启用  2禁用

    private String vid; // 村委会

    private String headerImg;// 头像

    private String userId;// 操作人

    private String fingerprint; // 指纹

    private String signature; // 签字

    private String instrType;// 辅导员类别 1 生产辅导员，2 烘烤辅导员

    private String province;// 所属省

    private String city;// 所属市

    private String county;// 所属县

    private int department;// 所在科室    81代表企管科长  86代表企管科员  82代表办公室主任  83代表办公室科员  84代表生产科长  85代表生产科员

    private String gender;// 性别

    private String birthday;// 出生年月

    private String hiredate; // 入职时间

    private String leavedate; // 离职时间

    private String education; // 文化程度

    private String nation; // 民族

    private String phone; // 平板手机号码

    private String uim; // UIM号

    private String discount; // 套餐

    private String discountDetail; // 套餐名细

    private String imei; // 串号

    private String vpdnNumber; // VPDN账号

    private String vpdnPassword; // VPDN密码

    private String regdate; // 注册日期

    private String agent; // 代理人
    
    private String instructorId; //安卓端主键
    
    private String orgName; //安卓端单位名称
    
    private String villageCommittee; //安卓端村委会
    
    private String towns; //安卓端乡镇


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getVillageGroup() {
        return villageGroup;
    }

    public void setVillageGroup(String villageGroup) {
        this.villageGroup = villageGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getInstrType() {
        return instrType;
    }

    public void setInstrType(String instrType) {
        this.instrType = instrType;
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

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUim() {
        return uim;
    }

    public void setUim(String uim) {
        this.uim = uim;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountDetail() {
        return discountDetail;
    }

    public void setDiscountDetail(String discountDetail) {
        this.discountDetail = discountDetail;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getVpdnNumber() {
        return vpdnNumber;
    }

    public void setVpdnNumber(String vpdnNumber) {
        this.vpdnNumber = vpdnNumber;
    }

    public String getVpdnPassword() {
        return vpdnPassword;
    }

    public void setVpdnPassword(String vpdnPassword) {
        this.vpdnPassword = vpdnPassword;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getVillageCommittee() {
        return villageCommittee;
    }

    public void setVillageCommittee(String villageCommittee) {
        this.villageCommittee = villageCommittee;
    }

    public String getTowns() {
        return towns;
    }

    public void setTowns(String towns) {
        this.towns = towns;
    }


    @Override
    public String toString() {
        return "Instructor{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", identity='" + identity + '\'' +
                ", tel='" + tel + '\'' +
                ", pid='" + pid + '\'' +
                ", uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", wid='" + wid + '\'' +
                ", villageGroup='" + villageGroup + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", available=" + available +
                ", vid='" + vid + '\'' +
                ", headerImg='" + headerImg + '\'' +
                ", userId='" + userId + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", signature='" + signature + '\'' +
                ", instrType='" + instrType + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", department=" + department +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", hiredate='" + hiredate + '\'' +
                ", leavedate='" + leavedate + '\'' +
                ", education='" + education + '\'' +
                ", nation='" + nation + '\'' +
                ", phone='" + phone + '\'' +
                ", uim='" + uim + '\'' +
                ", discount='" + discount + '\'' +
                ", discountDetail='" + discountDetail + '\'' +
                ", imei='" + imei + '\'' +
                ", vpdnNumber='" + vpdnNumber + '\'' +
                ", vpdnPassword='" + vpdnPassword + '\'' +
                ", regdate='" + regdate + '\'' +
                ", agent='" + agent + '\'' +
                ", instructorId='" + instructorId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", villageCommittee='" + villageCommittee + '\'' +
                ", towns='" + towns + '\'' +
                '}';
    }
}
