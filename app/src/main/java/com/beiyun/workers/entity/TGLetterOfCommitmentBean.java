package com.beiyun.workers.entity;


/**
 * Created by zqht on 2016/7/11 10:26
 * Email:zmm534635184@sina.com
 */
public class TGLetterOfCommitmentBean{

    public String fid;
    public String name;
    public String identity;
    public boolean status;
    public String year;
    public String townName;
    public String village;
    public String villageGroup;
    public String expectArea;
    public String variety;
    public String signature;
    public String fingerprint;
    public String promise;
    public String fingerModel;
    public String farmerTel;

    public String getFarmerTel() {
        return farmerTel;
    }

    public void setFarmerTel(String farmerTel) {
        this.farmerTel = farmerTel;
    }

    public String getExpectArea() {
        return expectArea;
    }

    public void setExpectArea(String expectArea) {
        this.expectArea = expectArea;
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

    public String getPromise() {
        return promise;
    }

    public void setPromise(String promise) {
        this.promise = promise;
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

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
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

    @Override
    public String toString() {
        return "TGLetterOfCommitmentBean{" +
                "fid='" + fid + '\'' +
                ", name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", status=" + status +
                ", year='" + year + '\'' +
                ", townName='" + townName + '\'' +
                ", village='" + village + '\'' +
                ", villageGroup='" + villageGroup + '\'' +
                ", expectArea='" + expectArea + '\'' +
                ", variety='" + variety + '\'' +
                ", signature='" + signature + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", promise='" + promise + '\'' +
                ", fingerModel='" + fingerModel + '\'' +
                ", farmerTel='" + farmerTel + '\'' +
                '}';
    }
}
