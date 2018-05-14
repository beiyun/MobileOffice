package com.beiyun.workers.entity;


import java.util.List;

/**
 * Created by 中旗 on 2016/7/14.
 * 清塘点株表
 */
public class SortAndCountEntity{
    public String psid;
    public String fid;
    public String para;//姓名或者身份证
    public String towns;//乡镇
    public String village;//村委会
    public String villageGroup;//村组
    public String name;//姓名
    public String variety;//品种
    public String sigArea;//合同面积
    public String sigStrains;//合同株树
    public String totalNum;//株树合计
    public String totalArea;//面积合计

    //上传
    public String year;//年度
    public String mandatoryAmount;//指令性收购
    public String exportAmount;//出口备货
    public String verification;//核实情况
    public List<SortStrainItemInfo> verifications;
    public String pointTime;//点株日期
    public String villageConfirm;//村组确认
    public String workerOpinion;
    public String workerSign;
    public String workerTime;
    public String stationOpinion;
    public String stationSign;
    public String stationTime;
    public String identity;//身份证
    public boolean status;//状态
    public String userId;//辅导员ID
    public String pid;
    public String farmerTel;

    public String getFarmerTel() {
        return farmerTel;
    }

    public void setFarmerTel(String farmerTel) {
        this.farmerTel = farmerTel;
    }

    public String getExportAmount() {
        return exportAmount;
    }

    public void setExportAmount(String exportAmount) {
        this.exportAmount = exportAmount;
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

    public String getMandatoryAmount() {
        return mandatoryAmount;
    }

    public void setMandatoryAmount(String mandatoryAmount) {
        this.mandatoryAmount = mandatoryAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPointTime() {
        return pointTime;
    }

    public void setPointTime(String pointTime) {
        this.pointTime = pointTime;
    }

    public String getPsid() {
        return psid;
    }

    public void setPsid(String psid) {
        this.psid = psid;
    }

    public String getSigArea() {
        return sigArea;
    }

    public void setSigArea(String sigArea) {
        this.sigArea = sigArea;
    }

    public String getSigStrains() {
        return sigStrains;
    }

    public void setSigStrains(String sigStrains) {
        this.sigStrains = sigStrains;
    }

    public String getStationOpinion() {
        return stationOpinion;
    }

    public void setStationOpinion(String stationOpinion) {
        this.stationOpinion = stationOpinion;
    }

    public String getStationSign() {
        return stationSign;
    }

    public void setStationSign(String stationSign) {
        this.stationSign = stationSign;
    }

    public String getStationTime() {
        return stationTime;
    }

    public void setStationTime(String stationTime) {
        this.stationTime = stationTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(String totalArea) {
        this.totalArea = totalArea;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getTowns() {
        return towns;
    }

    public void setTowns(String towns) {
        this.towns = towns;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public List<SortStrainItemInfo> getVerifications() {
        return verifications;
    }

    public void setVerifications(List<SortStrainItemInfo> verifications) {
        this.verifications = verifications;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getVillageConfirm() {
        return villageConfirm;
    }

    public void setVillageConfirm(String villageConfirm) {
        this.villageConfirm = villageConfirm;
    }

    public String getVillageGroup() {
        return villageGroup;
    }

    public void setVillageGroup(String villageGroup) {
        this.villageGroup = villageGroup;
    }

    public String getWorkerOpinion() {
        return workerOpinion;
    }

    public void setWorkerOpinion(String workerOpinion) {
        this.workerOpinion = workerOpinion;
    }

    public String getWorkerSign() {
        return workerSign;
    }

    public void setWorkerSign(String workerSign) {
        this.workerSign = workerSign;
    }

    public String getWorkerTime() {
        return workerTime;
    }

    public void setWorkerTime(String workerTime) {
        this.workerTime = workerTime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "SortAndCountEntity{" +
                "psid='" + psid + '\'' +
                ", fid='" + fid + '\'' +
                ", para='" + para + '\'' +
                ", towns='" + towns + '\'' +
                ", village='" + village + '\'' +
                ", villageGroup='" + villageGroup + '\'' +
                ", name='" + name + '\'' +
                ", variety='" + variety + '\'' +
                ", sigArea='" + sigArea + '\'' +
                ", sigStrains='" + sigStrains + '\'' +
                ", totalNum='" + totalNum + '\'' +
                ", totalArea='" + totalArea + '\'' +
                ", year='" + year + '\'' +
                ", mandatoryAmount='" + mandatoryAmount + '\'' +
                ", exportAmount='" + exportAmount + '\'' +
                ", verification='" + verification + '\'' +
                ", verifications=" + verifications +
                ", pointTime='" + pointTime + '\'' +
                ", villageConfirm='" + villageConfirm + '\'' +
                ", workerOpinion='" + workerOpinion + '\'' +
                ", workerSign='" + workerSign + '\'' +
                ", workerTime='" + workerTime + '\'' +
                ", stationOpinion='" + stationOpinion + '\'' +
                ", stationSign='" + stationSign + '\'' +
                ", stationTime='" + stationTime + '\'' +
                ", identity='" + identity + '\'' +
                ", status=" + status +
                ", userId='" + userId + '\'' +
                ", pid='" + pid + '\'' +
                ", farmerTel='" + farmerTel + '\'' +
                '}';
    }
}
