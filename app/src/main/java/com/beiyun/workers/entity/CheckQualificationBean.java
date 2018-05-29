package com.beiyun.workers.entity;


import java.util.List;

/**
 * Created by zqht on 2016/7/11 14:45
 * Email:zmm534635184@sina.com
 */
public class CheckQualificationBean {
    public String name;
    public String identity;
    public String year;
    public boolean status;
    public String farmerTel;

    /**
     * 种植申请单
     */
    public String aid;
    /**
     * 种烟信息Id
     */
    public String fid;
    /**
     * 资格审查Id
     */
    public String qid;
    /**
     * 申请单Id
     */
    public String pid;
    public String towns;
    public String village;
    public String villageGroup;
    public String applyArea;
    public String platTobaccoInfo;
    public String arableArea;
    public String leaseArea;
    public String combineArea;
    public String labour;
    public String executionRate;
    public String level;
    public String barnNumber;
    public String capacity;
    public String villageOpinion;
    public String villageSign;
    public String villageTime;
    public String workerOpinion;
    public String workerSign;
    public String workerTime;
    public String instructOpinion;
    public String instructSign;
    public String instructTime;
    public String stationOpinion;
    public String stationSign;
    public List<GrowTobaccoInfo> platTobaccoInfos;
    public String stationTime;
    public String certificate;
    public String projectSituation;
    public String denseBarn;
    public String previousArea;
    public String preceding;
    public String evaluationDegree;


    public String getFarmerTel() {
        return farmerTel;
    }

    public void setFarmerTel(String farmerTel) {
        this.farmerTel = farmerTel;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getApplyArea() {
        return applyArea;
    }

    public void setApplyArea(String applyArea) {
        this.applyArea = applyArea;
    }

    public String getArableArea() {
        return arableArea;
    }

    public void setArableArea(String arableArea) {
        this.arableArea = arableArea;
    }

    public String getBarnNumber() {
        return barnNumber;
    }

    public void setBarnNumber(String barnNumber) {
        this.barnNumber = barnNumber;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getCombineArea() {
        return combineArea;
    }

    public void setCombineArea(String combineArea) {
        this.combineArea = combineArea;
    }

    public String getDenseBarn() {
        return denseBarn;
    }

    public void setDenseBarn(String denseBarn) {
        this.denseBarn = denseBarn;
    }

    public String getEvaluationDegree() {
        return evaluationDegree;
    }

    public void setEvaluationDegree(String evaluationDegree) {
        this.evaluationDegree = evaluationDegree;
    }

    public String getExecutionRate() {
        return executionRate;
    }

    public void setExecutionRate(String executionRate) {
        this.executionRate = executionRate;
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

    public String getInstructOpinion() {
        return instructOpinion;
    }

    public void setInstructOpinion(String instructOpinion) {
        this.instructOpinion = instructOpinion;
    }

    public String getInstructSign() {
        return instructSign;
    }

    public void setInstructSign(String instructSign) {
        this.instructSign = instructSign;
    }

    public String getInstructTime() {
        return instructTime;
    }

    public void setInstructTime(String instructTime) {
        this.instructTime = instructTime;
    }

    public String getLabour() {
        return labour;
    }

    public void setLabour(String labour) {
        this.labour = labour;
    }

    public String getLeaseArea() {
        return leaseArea;
    }

    public void setLeaseArea(String leaseArea) {
        this.leaseArea = leaseArea;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPlatTobaccoInfo() {
        return platTobaccoInfo;
    }

    public void setPlatTobaccoInfo(String platTobaccoInfo) {
        this.platTobaccoInfo = platTobaccoInfo;
    }

    public List<GrowTobaccoInfo> getPlatTobaccoInfos() {
        return platTobaccoInfos;
    }

    public void setPlatTobaccoInfos(List<GrowTobaccoInfo> platTobaccoInfos) {
        this.platTobaccoInfos = platTobaccoInfos;
    }

    public String getPreceding() {
        return preceding;
    }

    public void setPreceding(String preceding) {
        this.preceding = preceding;
    }

    public String getPreviousArea() {
        return previousArea;
    }

    public void setPreviousArea(String previousArea) {
        this.previousArea = previousArea;
    }

    public String getProjectSituation() {
        return projectSituation;
    }

    public void setProjectSituation(String projectSituation) {
        this.projectSituation = projectSituation;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
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

    public String getVillageOpinion() {
        return villageOpinion;
    }

    public void setVillageOpinion(String villageOpinion) {
        this.villageOpinion = villageOpinion;
    }

    public String getVillageSign() {
        return villageSign;
    }

    public void setVillageSign(String villageSign) {
        this.villageSign = villageSign;
    }

    public String getVillageTime() {
        return villageTime;
    }

    public void setVillageTime(String villageTime) {
        this.villageTime = villageTime;
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
        return "CheckQualificationBean{" +
                "aid='" + aid + '\'' +
                ", name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", year='" + year + '\'' +
                ", status=" + status +
                ", fid='" + fid + '\'' +
                ", qid='" + qid + '\'' +
                ", pid='" + pid + '\'' +
                ", towns='" + towns + '\'' +
                ", village='" + village + '\'' +
                ", villageGroup='" + villageGroup + '\'' +
                ", applyArea='" + applyArea + '\'' +
                ", platTobaccoInfo='" + platTobaccoInfo + '\'' +
                ", arableArea='" + arableArea + '\'' +
                ", leaseArea='" + leaseArea + '\'' +
                ", combineArea='" + combineArea + '\'' +
                ", labour='" + labour + '\'' +
                ", executionRate='" + executionRate + '\'' +
                ", level='" + level + '\'' +
                ", barnNumber='" + barnNumber + '\'' +
                ", capacity='" + capacity + '\'' +
                ", villageOpinion='" + villageOpinion + '\'' +
                ", villageSign='" + villageSign + '\'' +
                ", villageTime='" + villageTime + '\'' +
                ", workerOpinion='" + workerOpinion + '\'' +
                ", workerSign='" + workerSign + '\'' +
                ", workerTime='" + workerTime + '\'' +
                ", instructOpinion='" + instructOpinion + '\'' +
                ", instructSign='" + instructSign + '\'' +
                ", instructTime='" + instructTime + '\'' +
                ", stationOpinion='" + stationOpinion + '\'' +
                ", stationSign='" + stationSign + '\'' +
                ", platTobaccoInfos=" + platTobaccoInfos +
                ", stationTime='" + stationTime + '\'' +
                ", certificate='" + certificate + '\'' +
                ", projectSituation='" + projectSituation + '\'' +
                ", denseBarn='" + denseBarn + '\'' +
                ", previousArea='" + previousArea + '\'' +
                ", preceding='" + preceding + '\'' +
                ", evaluationDegree='" + evaluationDegree + '\'' +
                '}';
    }
}
