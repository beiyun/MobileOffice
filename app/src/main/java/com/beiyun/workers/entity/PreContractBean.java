package com.beiyun.workers.entity;


/**
 * Created by zqht on 2016/7/11 15:35
 * Email:zmm534635184@sina.com
 */

public class PreContractBean{
    /**
     * 预签合同id
     */
    public String  rid;
    /**
     * 烟农id
     */
    public String  fid;
    public String  city;
    public String  year;
    public boolean  status;
    public String company;
    public String tobaccoPlot;
    public String name;
    public String identity;
    public String familyAddress;
    public String householdNo;
    public String tel;
    public String plantArea;
    public String strainNum;
    public String plantVariety;
    public String purchaseAmount;
    public String mandatoryAmount;
    public String exportAmount;
    public String jiaSign;
    public String jiaTime;
    public String yiSign;
    public String yiTime;
    public String farmerTel;

    public String getFarmerTel() {
        return farmerTel;
    }

    public void setFarmerTel(String farmerTel) {
        this.farmerTel = farmerTel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getExportAmount() {
        return exportAmount;
    }

    public void setExportAmount(String exportAmount) {
        this.exportAmount = exportAmount;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getHouseholdNo() {
        return householdNo;
    }

    public void setHouseholdNo(String householdNo) {
        this.householdNo = householdNo;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getJiaSign() {
        return jiaSign;
    }

    public void setJiaSign(String jiaSign) {
        this.jiaSign = jiaSign;
    }

    public String getJiaTime() {
        return jiaTime;
    }

    public void setJiaTime(String jiaTime) {
        this.jiaTime = jiaTime;
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

    public String getPlantArea() {
        return plantArea;
    }

    public void setPlantArea(String plantArea) {
        this.plantArea = plantArea;
    }

    public String getPlantVariety() {
        return plantVariety;
    }

    public void setPlantVariety(String plantVariety) {
        this.plantVariety = plantVariety;
    }

    public String getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStrainNum() {
        return strainNum;
    }

    public void setStrainNum(String strainNum) {
        this.strainNum = strainNum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTobaccoPlot() {
        return tobaccoPlot;
    }

    public void setTobaccoPlot(String tobaccoPlot) {
        this.tobaccoPlot = tobaccoPlot;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYiSign() {
        return yiSign;
    }

    public void setYiSign(String yiSign) {
        this.yiSign = yiSign;
    }

    public String getYiTime() {
        return yiTime;
    }

    public void setYiTime(String yiTime) {
        this.yiTime = yiTime;
    }

    @Override
    public String toString() {
        return "PreContractBean{" +
                "rid='" + rid + '\'' +
                ", fid='" + fid + '\'' +
                ", city='" + city + '\'' +
                ", year='" + year + '\'' +
                ", status=" + status +
                ", company='" + company + '\'' +
                ", tobaccoPlot='" + tobaccoPlot + '\'' +
                ", name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", familyAddress='" + familyAddress + '\'' +
                ", householdNo='" + householdNo + '\'' +
                ", tel='" + tel + '\'' +
                ", plantArea='" + plantArea + '\'' +
                ", strainNum='" + strainNum + '\'' +
                ", plantVariety='" + plantVariety + '\'' +
                ", purchaseAmount='" + purchaseAmount + '\'' +
                ", mandatoryAmount='" + mandatoryAmount + '\'' +
                ", exportAmount='" + exportAmount + '\'' +
                ", jiaSign='" + jiaSign + '\'' +
                ", jiaTime='" + jiaTime + '\'' +
                ", yiSign='" + yiSign + '\'' +
                ", yiTime='" + yiTime + '\'' +
                ", farmerTel='" + farmerTel + '\'' +
                '}';
    }
}
