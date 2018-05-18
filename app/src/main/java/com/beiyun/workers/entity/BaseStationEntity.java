package com.beiyun.workers.entity;


import java.util.ArrayList;

/**
 * Created by beiyun on 2017/3/15.
 * 基础设施
 */
public class BaseStationEntity {

    private String bfid;//id

    private String oneType; // 一级分类

    private String secondType; // 二级分类

    private String countyCode; // 区域-县

    private String townCode; // 区域-镇

    private String villageCode; // 区域-村

    private String groupCode; // 区域-村小组

    private String mainSubject; // 建设主体

    private String linkTel; // 联系电话

    private String header; // 指定负责人

    private String workUnit; // 工作单位

    private String itemNo; // 项目编号

    private String place; // 建设地点

    private String longitude; // 经度

    private String latitude; // 纬度

    private String endLongitude;//终点经度

    private String endLatitude;//终点维度

    private String beUsed; // 使用情况

    private String execUnit; // 施工单位

    private String benefitedArea;// 受益面积

    private String capacity; // 容量 （立方米）

    private String constrContent; // 建设内容

    private String pipMater; // 管道材质、规格长度

    private String purchaseBody; // 购置主体

    private String orgCode; // 组织机构代码

    private String installSmokeType;// 装烟器具类型

    private String supplier; // 供应商

    private String barnSupplier; // 烤房设备供应厂家

    private String specification; // 建造规格

    private String quantity; // 购置数量

    private String purchaseTime; // 购置时间

    private String machineName; // 农机名称

    private String brand; // 品牌型号

    private String proEnterprise; // 生产企业

    private String power; // 功率

    private String qualifiedNo; // 出厂合格号

    private String engineNo; // 发动机号

    private String frameNo; // 机架号

    private String efficiency; // 作业效率

    private String supplyUnit; // 施工（供货）单位

    private String constrArea; // 建设面积

    private String nurseryArea; // 育苗面积

    private String transArea; // 可供大田移栽面积

    private String totalCost; // 总造价

    private String subsidies; // 补贴资金

    private String startTime; // 开工时间

    private String completedTime; // 竣工时间

    private String manager; // 管护责任人

    private ArrayList<StationImages> images;//照片

    private String molectronics;//调制能力

    public static class StationImages{
        public String image;

        public StationImages(String image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return "StationImages{" +
                    "image='" + image + '\'' +
                    '}';
        }
    }

    public String getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(String endLatitude) {
        this.endLatitude = endLatitude;
    }

    public String getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(String endLongitude) {
        this.endLongitude = endLongitude;
    }

    public String getMolectronics() {
        return molectronics;
    }

    public void setMolectronics(String molectronics) {
        this.molectronics = molectronics;
    }

    public String getBarnSupplier() {
        return barnSupplier;

    }

    public String getBfid() {
        return bfid;
    }

    public void setBfid(String bfid) {
        this.bfid = bfid;
    }

    public void setBarnSupplier(String barnSupplier) {
        this.barnSupplier = barnSupplier;
    }

    public String getBenefitedArea() {
        return benefitedArea;
    }

    public void setBenefitedArea(String benefitedArea) {
        this.benefitedArea = benefitedArea;
    }

    public String getBeUsed() {
        return beUsed;
    }

    public void setBeUsed(String beUsed) {
        this.beUsed = beUsed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(String completedTime) {
        this.completedTime = completedTime;
    }

    public String getConstrArea() {
        return constrArea;
    }

    public void setConstrArea(String constrArea) {
        this.constrArea = constrArea;
    }

    public String getConstrContent() {
        return constrContent;
    }

    public void setConstrContent(String constrContent) {
        this.constrContent = constrContent;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getExecUnit() {
        return execUnit;
    }

    public void setExecUnit(String execUnit) {
        this.execUnit = execUnit;
    }

    public String getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(String frameNo) {
        this.frameNo = frameNo;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getInstallSmokeType() {
        return installSmokeType;
    }

    public void setInstallSmokeType(String installSmokeType) {
        this.installSmokeType = installSmokeType;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMainSubject() {
        return mainSubject;
    }

    public void setMainSubject(String mainSubject) {
        this.mainSubject = mainSubject;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getNurseryArea() {
        return nurseryArea;
    }

    public void setNurseryArea(String nurseryArea) {
        this.nurseryArea = nurseryArea;
    }

    public String getOneType() {
        return oneType;
    }

    public void setOneType(String oneType) {
        this.oneType = oneType;
    }

    public String getPipMater() {
        return pipMater;
    }

    public void setPipMater(String pipMater) {
        this.pipMater = pipMater;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getProEnterprise() {
        return proEnterprise;
    }

    public void setProEnterprise(String proEnterprise) {
        this.proEnterprise = proEnterprise;
    }

    public String getPurchaseBody() {
        return purchaseBody;
    }

    public void setPurchaseBody(String purchaseBody) {
        this.purchaseBody = purchaseBody;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getQualifiedNo() {
        return qualifiedNo;
    }

    public void setQualifiedNo(String qualifiedNo) {
        this.qualifiedNo = qualifiedNo;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSubsidies() {
        return subsidies;
    }

    public void setSubsidies(String subsidies) {
        this.subsidies = subsidies;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplyUnit() {
        return supplyUnit;
    }

    public void setSupplyUnit(String supplyUnit) {
        this.supplyUnit = supplyUnit;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getTransArea() {
        return transArea;
    }

    public void setTransArea(String transArea) {
        this.transArea = transArea;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public ArrayList<StationImages> getImages() {
        return images;
    }

    public void setImages(ArrayList<StationImages> images) {
        this.images = images;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public String toString() {
        return "BaseStationEntity{" +
                "barnSupplier='" + barnSupplier + '\'' +
                ", oneType='" + oneType + '\'' +
                ", secondType='" + secondType + '\'' +
                ", countyCode='" + countyCode + '\'' +
                ", townCode='" + townCode + '\'' +
                ", villageCode='" + villageCode + '\'' +
                ", groupCode='" + groupCode + '\'' +
                ", mainSubject='" + mainSubject + '\'' +
                ", linkTel='" + linkTel + '\'' +
                ", header='" + header + '\'' +
                ", workUnit='" + workUnit + '\'' +
                ", itemNo='" + itemNo + '\'' +
                ", place='" + place + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", beUsed='" + beUsed + '\'' +
                ", execUnit='" + execUnit + '\'' +
                ", benefitedArea='" + benefitedArea + '\'' +
                ", capacity='" + capacity + '\'' +
                ", constrContent='" + constrContent + '\'' +
                ", pipMater='" + pipMater + '\'' +
                ", purchaseBody='" + purchaseBody + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", installSmokeType='" + installSmokeType + '\'' +
                ", supplier='" + supplier + '\'' +
                ", specification='" + specification + '\'' +
                ", quantity='" + quantity + '\'' +
                ", purchaseTime='" + purchaseTime + '\'' +
                ", machineName='" + machineName + '\'' +
                ", brand='" + brand + '\'' +
                ", proEnterprise='" + proEnterprise + '\'' +
                ", power='" + power + '\'' +
                ", qualifiedNo='" + qualifiedNo + '\'' +
                ", engineNo='" + engineNo + '\'' +
                ", frameNo='" + frameNo + '\'' +
                ", efficiency='" + efficiency + '\'' +
                ", supplyUnit='" + supplyUnit + '\'' +
                ", constrArea='" + constrArea + '\'' +
                ", nurseryArea='" + nurseryArea + '\'' +
                ", transArea='" + transArea + '\'' +
                ", totalCost='" + totalCost + '\'' +
                ", subsidies='" + subsidies + '\'' +
                ", startTime='" + startTime + '\'' +
                ", completedTime='" + completedTime + '\'' +
                ", manager='" + manager + '\'' +
                ", images=" + images +
                '}';
    }
}
