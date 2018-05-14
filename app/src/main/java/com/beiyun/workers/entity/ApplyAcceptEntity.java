package com.beiyun.workers.entity;


/**
 * Created by 中旗 on 2016/7/13.
 * 申请受理表
 */
public class ApplyAcceptEntity {


    public String  pid;
    public String  fid;//烟农编号
    public String  name;	//烟农姓名
    public String  village;//	村委会
    public String villageGroup;//村小组
    public String  accountNumber;	//	户口册号
    public String  applyNumber;	//	编号
    public String  agreeArea;//	同意种植面积
    public String  agreeTian;//	同意种植面积:田
    public String  agreeDi;//	同意种植面积：地
    public String  agreeVariety;//种植品种
    public String  auditUnit;//	甲方签字
    public String  auditTime;//	签字时间
    public String  rejectedReason;//	申请驳回原因


    public String identity;
    public String year;//年度
    public boolean status;

    //要上传
    public String  arableArea	;//	自有耕地面积（亩
    public String  leaseArea;	//	租赁面积（亩）
    public String  tianArea;//	田烟面积（亩）
    public String  diArea;//	地烟面积（亩）
    public String  applyArea;//	申请种植面积
    public String  applyTian;//	申请种植面积：田
    public String  applyDi;//申请种植面积：地
    public String  city;//	州，市
    public String  insureCost;//	保费
    public String  insureArea;//投保亩数
    public String  partyBSignature;//	乙方签字
    public String  declareTime;//	签字时间
    public String farmerTel;

    public String getFarmerTel() {
        return farmerTel;
    }

    public void setFarmerTel(String farmerTel) {
        this.farmerTel = farmerTel;
    }

    public String getVillageGroup() {
        return villageGroup;
    }

    public void setVillageGroup(String villageGroup) {
        this.villageGroup = villageGroup;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAgreeArea() {
        return agreeArea;
    }

    public void setAgreeArea(String agreeArea) {
        this.agreeArea = agreeArea;
    }

    public String getAgreeDi() {
        return agreeDi;
    }

    public void setAgreeDi(String agreeDi) {
        this.agreeDi = agreeDi;
    }

    public String getAgreeTian() {
        return agreeTian;
    }

    public void setAgreeTian(String agreeTian) {
        this.agreeTian = agreeTian;
    }

    public String getAgreeVariety() {
        return agreeVariety;
    }

    public void setAgreeVariety(String agreeVariety) {
        this.agreeVariety = agreeVariety;
    }

    public String getApplyArea() {
        return applyArea;
    }

    public void setApplyArea(String applyArea) {
        this.applyArea = applyArea;
    }

    public String getApplyDi() {
        return applyDi;
    }

    public void setApplyDi(String applyDi) {
        this.applyDi = applyDi;
    }

    public String getApplyNumber() {
        return applyNumber;
    }

    public void setApplyNumber(String applyNumber) {
        this.applyNumber = applyNumber;
    }

    public String getApplyTian() {
        return applyTian;
    }

    public void setApplyTian(String applyTian) {
        this.applyTian = applyTian;
    }

    public String getArableArea() {
        return arableArea;
    }

    public void setArableArea(String arableArea) {
        this.arableArea = arableArea;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditUnit() {
        return auditUnit;
    }

    public void setAuditUnit(String auditUnit) {
        this.auditUnit = auditUnit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDeclareTime() {
        return declareTime;
    }

    public void setDeclareTime(String declareTime) {
        this.declareTime = declareTime;
    }

    public String getDiArea() {
        return diArea;
    }

    public void setDiArea(String diArea) {
        this.diArea = diArea;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getInsureArea() {
        return insureArea;
    }

    public void setInsureArea(String insureArea) {
        this.insureArea = insureArea;
    }

    public String getInsureCost() {
        return insureCost;
    }

    public void setInsureCost(String insureCost) {
        this.insureCost = insureCost;
    }

    public String getLeaseArea() {
        return leaseArea;
    }

    public void setLeaseArea(String leaseArea) {
        this.leaseArea = leaseArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartyBSignature() {
        return partyBSignature;
    }

    public void setPartyBSignature(String partyBSignature) {
        this.partyBSignature = partyBSignature;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTianArea() {
        return tianArea;
    }

    public void setTianArea(String tianArea) {
        this.tianArea = tianArea;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "ApplyAcceptEntity{" +
                "pid='" + pid + '\'' +
                ", fid='" + fid + '\'' +
                ", name='" + name + '\'' +
                ", village='" + village + '\'' +
                ", villageGroup='" + villageGroup + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", applyNumber='" + applyNumber + '\'' +
                ", agreeArea='" + agreeArea + '\'' +
                ", agreeTian='" + agreeTian + '\'' +
                ", agreeDi='" + agreeDi + '\'' +
                ", agreeVariety='" + agreeVariety + '\'' +
                ", auditUnit='" + auditUnit + '\'' +
                ", auditTime='" + auditTime + '\'' +
                ", rejectedReason='" + rejectedReason + '\'' +
                ", identity='" + identity + '\'' +
                ", year='" + year + '\'' +
                ", status=" + status +
                ", arableArea='" + arableArea + '\'' +
                ", leaseArea='" + leaseArea + '\'' +
                ", tianArea='" + tianArea + '\'' +
                ", diArea='" + diArea + '\'' +
                ", applyArea='" + applyArea + '\'' +
                ", applyTian='" + applyTian + '\'' +
                ", applyDi='" + applyDi + '\'' +
                ", city='" + city + '\'' +
                ", insureCost='" + insureCost + '\'' +
                ", insureArea='" + insureArea + '\'' +
                ", partyBSignature='" + partyBSignature + '\'' +
                ", declareTime='" + declareTime + '\'' +
                '}';

    }
}
