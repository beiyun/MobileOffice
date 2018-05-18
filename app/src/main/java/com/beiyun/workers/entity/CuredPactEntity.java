package com.beiyun.workers.entity;


/**
 * Created by beiyun on 2017/4/18.
 * 烤烟协议
 */
public class CuredPactEntity {

    private String userId;
    private String name;    //	用户姓名
    private boolean status;//状态
    private String identity;    //	身份证
    private String farmerId;
    private String years;//年度
    private String partyA;//甲方
    private String partyB;//乙方
    private String fees;//收费标准
    private String signatureA;//甲方签字
    private String signatureB;//乙方签字
    private String signTime;//签字时间
    private String farmerTel;

    public String getFarmerTel() {
        return farmerTel;
    }

    public void setFarmerTel(String farmerTel) {
        this.farmerTel = farmerTel;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getPartyA() {
        return partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    public String getSignatureA() {
        return signatureA;
    }

    public void setSignatureA(String signatureA) {
        this.signatureA = signatureA;
    }

    public String getSignatureB() {
        return signatureB;
    }

    public void setSignatureB(String signatureB) {
        this.signatureB = signatureB;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return "CuredPactEntity{" +
                "farmerId='" + farmerId + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", identity='" + identity + '\'' +
                ", years='" + years + '\'' +
                ", partyA='" + partyA + '\'' +
                ", partyB='" + partyB + '\'' +
                ", fees='" + fees + '\'' +
                ", signatureA='" + signatureA + '\'' +
                ", signatureB='" + signatureB + '\'' +
                ", signTime='" + signTime + '\'' +
                '}';
    }
}
