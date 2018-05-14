package com.beiyun.workers.entity;


import java.util.List;

/**
 * Created by zqht on 2016/7/14 10:25
 * Email:zmm534635184@sina.com
 */
public class FormalContractBean {

    public  String fpid;
    public String year;
    public String fid;
    public String name;
    public String identity;
    public boolean status;
    public String partAName;
    public String partBName;
    public String householdNo;
    public String linkTel;
    public String familyAddress;
    public String bank;
    public String bankAccount;
    public String tobaccoPlot;
    public List<FieldItemInfo>fieldInfo;
    public String fieldInfoString;
    public String platArea;
    public String strainNum;
    public String mandatoryAmount;
    public String exportAmount;
    public String adjust1Area;
    public String adjust1Strain;
    public String adjust1Mandatory;
    public String adjust1Export;
    public String adjust2Area;
    public String adjust2Strain;
    public String adjust2Mandatory;
    public String adjust2Export;
    public String purchasePeriod;
    public String purchasePlace;
    public List<SubsidyItemInfo> subsidyInfo;

    public String getFieldInfoString() {
        return fieldInfoString;
    }

    public void setFieldInfoString(String fieldInfoString) {
        this.fieldInfoString = fieldInfoString;
    }

    public String getSubsidyInfoString() {
        return subsidyInfoString;
    }

    public void setSubsidyInfoString(String subsidyInfoString) {
        this.subsidyInfoString = subsidyInfoString;
    }

    public String subsidyInfoString;
    public String term1;
    public String term2;
    public String term3;
    public String jiaSign;
    public String yiSign;
    public String signTime;
    public String yiSignTime;


    @Override
    public String toString() {
        return "FormalContractBean{" +
                "adjust1Area='" + adjust1Area + '\'' +
                ", fpid='" + fpid + '\'' +
                ", year='" + year + '\'' +
                ", fid='" + fid + '\'' +
                ", name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", status=" + status +
                ", partAName='" + partAName + '\'' +
                ", partBName='" + partBName + '\'' +
                ", householdNo='" + householdNo + '\'' +
                ", linkTel='" + linkTel + '\'' +
                ", familyAddress='" + familyAddress + '\'' +
                ", bank='" + bank + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", tobaccoPlot='" + tobaccoPlot + '\'' +
                ", fieldInfo=" + fieldInfo +
                ", platArea='" + platArea + '\'' +
                ", strainNum='" + strainNum + '\'' +
                ", mandatoryAmount='" + mandatoryAmount + '\'' +
                ", exportAmount='" + exportAmount + '\'' +
                ", adjust1Strain='" + adjust1Strain + '\'' +
                ", adjust1Mandatory='" + adjust1Mandatory + '\'' +
                ", adjust1Export='" + adjust1Export + '\'' +
                ", adjust2Area='" + adjust2Area + '\'' +
                ", adjust2Strain='" + adjust2Strain + '\'' +
                ", adjust2Mandatory='" + adjust2Mandatory + '\'' +
                ", adjust2Export='" + adjust2Export + '\'' +
                ", purchasePeriod='" + purchasePeriod + '\'' +
                ", purchasePlace='" + purchasePlace + '\'' +
                ", subsidyInfo=" + subsidyInfo +
                ", term1='" + term1 + '\'' +
                ", term2='" + term2 + '\'' +
                ", term3='" + term3 + '\'' +
                ", jiaSign='" + jiaSign + '\'' +
                ", yiSign='" + yiSign + '\'' +
                ", signTime='" + signTime + '\'' +
                ", yiSignTime='" + yiSignTime + '\'' +
                '}';
    }

    public String getAdjust1Area() {
        return adjust1Area;
    }

    public void setAdjust1Area(String adjust1Area) {
        this.adjust1Area = adjust1Area;
    }

    public String getAdjust1Export() {
        return adjust1Export;
    }

    public void setAdjust1Export(String adjust1Export) {
        this.adjust1Export = adjust1Export;
    }

    public String getAdjust1Mandatory() {
        return adjust1Mandatory;
    }

    public void setAdjust1Mandatory(String adjust1Mandatory) {
        this.adjust1Mandatory = adjust1Mandatory;
    }

    public String getAdjust1Strain() {
        return adjust1Strain;
    }

    public void setAdjust1Strain(String adjust1Strain) {
        this.adjust1Strain = adjust1Strain;
    }

    public String getAdjust2Area() {
        return adjust2Area;
    }

    public void setAdjust2Area(String adjust2Area) {
        this.adjust2Area = adjust2Area;
    }

    public String getAdjust2Export() {
        return adjust2Export;
    }

    public void setAdjust2Export(String adjust2Export) {
        this.adjust2Export = adjust2Export;
    }

    public String getAdjust2Mandatory() {
        return adjust2Mandatory;
    }

    public void setAdjust2Mandatory(String adjust2Mandatory) {
        this.adjust2Mandatory = adjust2Mandatory;
    }

    public String getAdjust2Strain() {
        return adjust2Strain;
    }

    public void setAdjust2Strain(String adjust2Strain) {
        this.adjust2Strain = adjust2Strain;
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

    public List<FieldItemInfo> getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(List<FieldItemInfo> fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getFpid() {
        return fpid;
    }

    public void setFpid(String fpid) {
        this.fpid = fpid;
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

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
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

    public String getPartAName() {
        return partAName;
    }

    public void setPartAName(String partAName) {
        this.partAName = partAName;
    }

    public String getPartBName() {
        return partBName;
    }

    public void setPartBName(String partBName) {
        this.partBName = partBName;
    }

    public String getPlatArea() {
        return platArea;
    }

    public void setPlatArea(String platArea) {
        this.platArea = platArea;
    }

    public String getPurchasePeriod() {
        return purchasePeriod;
    }

    public void setPurchasePeriod(String purchasePeriod) {
        this.purchasePeriod = purchasePeriod;
    }

    public String getPurchasePlace() {
        return purchasePlace;
    }

    public void setPurchasePlace(String purchasePlace) {
        this.purchasePlace = purchasePlace;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
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

    public List<SubsidyItemInfo> getSubsidyInfo() {
        return subsidyInfo;
    }

    public void setSubsidyInfo(List<SubsidyItemInfo> subsidyInfo) {
        this.subsidyInfo = subsidyInfo;
    }

    public String getTerm1() {
        return term1;
    }

    public void setTerm1(String term1) {
        this.term1 = term1;
    }

    public String getTerm2() {
        return term2;
    }

    public void setTerm2(String term2) {
        this.term2 = term2;
    }

    public String getTerm3() {
        return term3;
    }

    public void setTerm3(String term3) {
        this.term3 = term3;
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

    public String getYiSignTime() {
        return yiSignTime;
    }

    public void setYiSignTime(String yiSignTime) {
        this.yiSignTime = yiSignTime;
    }
}
