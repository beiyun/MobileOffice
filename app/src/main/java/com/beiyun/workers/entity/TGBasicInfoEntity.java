package com.beiyun.workers.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 中旗 on 2016/7/13.
 * 烟农基本信息实体类
 */
public class TGBasicInfoEntity{

    //搜索人员类型
    private int category;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


    private String fid;//主鍵ID，信息修改需要返回給後臺
    private String name;    //	用户姓名
    private String linkTel;    //	联系电话
    private String year;//年度
    private boolean status;//状态
    private String identity;    //	身份证号
    private String towns;    //乡镇
    private String village;    //	村委会
    private String villageGroup;    //	村组
    private String sex;    //	性别
    private String age;    //	年龄
    private String labour;    //	劳动力
    private String accountNumber;    //	户号
    private String education;    //	学历
    private String familyMember;    //	家庭人口
    private String bankAccount;    //	银行账号
    private String bank;    //	开户行
    private String headImg;//烟农照片
    private String positiveImg;    //身份证正面
    private String oppositeImg;    //	身份证反面
    private String homeImg;    //	户口本首页
    private String signature;//烟农签名
    private String fingerprint;//烟农指纹
    private String fingerModel;//烟农比对源
    private ArrayList<TGBasicInfoItemEntity> dataList;//田烟基本信息
    private String dataListString;//田烟基本信息
    private String tid;

    //办卡信息
    private String cPhone;//手机号
    private String cUIM;//UIM好
    private String cSet;//套餐
    private String cSetDetail;//套餐明细
    private String cNumber;//串号
    private String cAccount;//VPDN账号
    private String cPassword;//VPDN密码
    private String cDate;//开通日期
    private String cProxy;//代理人
    

    //往年种烟信息list(烟农管理查询可用)
    private List<GrowInfo> farmerDetail;



    private String id; // ID

    private String userName; // 用户名

    private String userNumber; // 用户账号

    private String nickname; // 职工姓名

    private String password; // 用户密码

    private String type; // 职工类别      1代表烟站站长  2代表烟站职工  3代表辅导员  41代表省领导  42代表省职工  43代表市领导  44代表市职工  45代表县领导  46代表县职工

//    private String identity; // 身份证号

    private String tel; // 联系电话

    private String pid; // 省市县id,type=6 省id type =7 市id type =8 县id

    private String uid; // 单位名称ID

    private String uname; // 单位名称

    private String wid; // 挂钩职工

//    private String villageGroup; // 下属村组

    private String address; // 家庭住址

    private String remark; // 备注

    private String available; // 状态   1启用  2禁用

    private String vid; // 村委会

    private String headerImg;// 头像

    private String userId;// 操作人

//    private String fingerprint; // 指纹
//
//    private String signature; // 签字

    private String instrType;// 辅导员类别 1 生产辅导员，2 烘烤辅导员

    private String province;// 所属省

    private String city;// 所属市

    private String county;// 所属县

    private String department;// 所在科室    81代表企管科长  86代表企管科员  82代表办公室主任  83代表办公室科员  84代表生产科长  85代表生产科员

    private String gender;// 性别

    private String birthday;// 出生年月

    private String hiredate; // 入职时间

    private String leavedate; // 离职时间

//    private String education; // 文化程度

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

//    private String towns; //安卓端乡镇



    //往年种烟信息
    public class GrowInfo{

        private String annual;//年度
        private String arableArea;//耕地面积
        private String leaseArea;//租赁面积
        private String preceding;//前茬
        private String combineArea;//当年种烟合计
        private String tianArea;//田烟面积
        private String diArea;//地烟面积
        private String variety;//种植品种
        private String strains;//种植株数
        private String mandatoryAmount;//指令性量
        private String exportAmount;//其中出口备货


        @Override
        public String toString() {
            return "GrowInfo{" +
                    "annual='" + annual + '\'' +
                    ", arableArea='" + arableArea + '\'' +
                    ", leaseArea='" + leaseArea + '\'' +
                    ", preceding='" + preceding + '\'' +
                    ", combineArea='" + combineArea + '\'' +
                    ", tianArea='" + tianArea + '\'' +
                    ", diArea='" + diArea + '\'' +
                    ", variety='" + variety + '\'' +
                    ", strains='" + strains + '\'' +
                    ", mandatoryAmount='" + mandatoryAmount + '\'' +
                    ", exportAmount='" + exportAmount + '\'' +
                    '}';
        }

        public String getAnnual() {
            return annual;
        }

        public void setAnnual(String annual) {
            this.annual = annual;
        }

        public String getArableArea() {
            return arableArea;
        }

        public void setArableArea(String arableArea) {
            this.arableArea = arableArea;
        }

        public String getCombineArea() {
            return combineArea;
        }

        public void setCombineArea(String combineArea) {
            this.combineArea = combineArea;
        }

        public String getDiArea() {
            return diArea;
        }

        public void setDiArea(String diArea) {
            this.diArea = diArea;
        }

        public String getExportAmount() {
            return exportAmount;
        }

        public void setExportAmount(String exportAmount) {
            this.exportAmount = exportAmount;
        }

        public String getLeaseArea() {
            return leaseArea;
        }

        public void setLeaseArea(String leaseArea) {
            this.leaseArea = leaseArea;
        }

        public String getMandatoryAmount() {
            return mandatoryAmount;
        }

        public void setMandatoryAmount(String mandatoryAmount) {
            this.mandatoryAmount = mandatoryAmount;
        }

        public String getPreceding() {
            return preceding;
        }

        public void setPreceding(String preceding) {
            this.preceding = preceding;
        }

        public String getStrains() {
            return strains;
        }

        public void setStrains(String strains) {
            this.strains = strains;
        }

        public String getTianArea() {
            return tianArea;
        }

        public void setTianArea(String tianArea) {
            this.tianArea = tianArea;
        }

        public String getVariety() {
            return variety;
        }

        public void setVariety(String variety) {
            this.variety = variety;
        }
    }

    public String getcAccount() {
        return cAccount;
    }

    public void setcAccount(String cAccount) {
        this.cAccount = cAccount;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public String getcNumber() {
        return cNumber;
    }

    public void setcNumber(String cNumber) {
        this.cNumber = cNumber;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public String getcProxy() {
        return cProxy;
    }

    public void setcProxy(String cProxy) {
        this.cProxy = cProxy;
    }

    public String getcSet() {
        return cSet;
    }

    public void setcSet(String cSet) {
        this.cSet = cSet;
    }

    public String getcSetDetail() {
        return cSetDetail;
    }

    public void setcSetDetail(String cSetDetail) {
        this.cSetDetail = cSetDetail;
    }

    public String getcUIM() {
        return cUIM;
    }

    public void setcUIM(String cUIM) {
        this.cUIM = cUIM;
    }

    public List<GrowInfo> getFarmerDetail() {
        return farmerDetail;
    }

    public void setFarmerDetail(List<GrowInfo> farmerDetail) {
        this.farmerDetail = farmerDetail;
    }

    public String getDataListString() {
        return dataListString;
    }

    public void setDataListString(String dataListString) {
        this.dataListString = dataListString;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public ArrayList<TGBasicInfoItemEntity> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<TGBasicInfoItemEntity> dataList) {
        this.dataList = dataList;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(String familyMember) {
        this.familyMember = familyMember;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFingerModel() {
        return fingerModel;
    }

    public void setFingerModel(String fingerModel) {
        this.fingerModel = fingerModel;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getHomeImg() {
        return homeImg;
    }

    public void setHomeImg(String homeImg) {
        this.homeImg = homeImg;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getLabour() {
        return labour;
    }

    public void setLabour(String labour) {
        this.labour = labour;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOppositeImg() {
        return oppositeImg;
    }

    public void setOppositeImg(String oppositeImg) {
        this.oppositeImg = oppositeImg;
    }

    public String getPositiveImg() {
        return positiveImg;
    }

    public void setPositiveImg(String positiveImg) {
        this.positiveImg = positiveImg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTowns() {
        return towns;
    }

    public void setTowns(String towns) {
        this.towns = towns;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
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

    @Override
    public String toString() {
        return "TGBasicInfoEntity >>{" +
                "category='" + category + '\'' +
                "accountNumber='" + accountNumber + '\'' +
                ", fid='" + fid + '\'' +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", status=" + status +
                ", identity='" + identity + '\'' +
                ", linkTel='" + linkTel + '\'' +
                ", towns='" + towns + '\'' +
                ", village='" + village + '\'' +
                ", villageGroup='" + villageGroup + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", labour='" + labour + '\'' +
                ", education='" + education + '\'' +
                ", familyMember='" + familyMember + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", bank='" + bank + '\'' +
                ", headImg='" + headImg + '\'' +
                ", positiveImg='" + positiveImg + '\'' +
                ", oppositeImg='" + oppositeImg + '\'' +
                ", homeImg='" + homeImg + '\'' +
                ", signature='" + signature + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", fingerModel='" + fingerModel + '\'' +
                ", dataList=" + dataList +
                ", dataListString='" + dataListString + '\'' +
                ", tid='" + tid + '\'' +
                ", cPhone='" + cPhone + '\'' +
                ", cUIM='" + cUIM + '\'' +
                ", cSet='" + cSet + '\'' +
                ", cSetDetail='" + cSetDetail + '\'' +
                ", cNumber='" + cNumber + '\'' +
                ", cAccount='" + cAccount + '\'' +
                ", cPassword='" + cPassword + '\'' +
                ", cDate='" + cDate + '\'' +
                ", cProxy='" + cProxy + '\'' +
                ", farmerDetail=" + farmerDetail +
                '}'+"\n " +

                "Instructor info >>>{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", tel='" + tel + '\'' +
                ", pid='" + pid + '\'' +
                ", uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", wid='" + wid + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", available=" + available +
                ", vid='" + vid + '\'' +
                ", headerImg='" + headerImg + '\'' +
                ", userId='" + userId + '\'' +
                ", instrType='" + instrType + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", department=" + department +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", hiredate='" + hiredate + '\'' +
                ", leavedate='" + leavedate + '\'' +
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
                '}';
    }



}
