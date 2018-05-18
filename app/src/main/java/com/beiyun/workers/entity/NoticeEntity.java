package com.beiyun.workers.entity;

/**
 * Created by beiyun on 2017/5/3.
 * 预约通知单
 */
public class NoticeEntity {

    private String userId;
    private String name; //烟农姓名
    private String years;//年度
    private boolean status;//状态
    private String identity; //身份证
    private String farmerId;
    private String points;//村组
    private String phone;//联系电话
    private String weight;//估重数量
    private String classificationPlace;//集中分级地点
    private String startDate;//集中分级开始时段
    private String startTime;//集中分级开始时段
    private String endDate;//集中分级结束时段
    private String endTime;//集中分级结束时段
    private String sellPlace;//烟叶交售地点
    private String beginDate;//预约交售开始时段
    private String beginTime;//预约交售开始时段
    private String finishDate;//预约交售结束时段
    private String finishTime;//预约交售结束时段
    private String signature;//操作人签字
    private String signTime;//签字时间


    @Override
    public String toString() {
        return "NoticeEntity{" +
                "beginDate='" + beginDate + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", years='" + years + '\'' +
                ", status=" + status +
                ", identity='" + identity + '\'' +
                ", farmerId='" + farmerId + '\'' +
                ", points='" + points + '\'' +
                ", phone='" + phone + '\'' +
                ", weight='" + weight + '\'' +
                ", classificationPlace='" + classificationPlace + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", entDate='" + endDate + '\'' +
                ", endTime='" + endTime + '\'' +
                ", sellPlace='" + sellPlace + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", signTime='" + signTime + '\'' +
                '}';
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getClassificationPlace() {
        return classificationPlace;
    }

    public void setClassificationPlace(String classificationPlace) {
        this.classificationPlace = classificationPlace;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEntDate() {
        return endDate;
    }

    public void setEntDate(String entDate) {
        this.endDate = entDate;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getSellPlace() {
        return sellPlace;
    }

    public void setSellPlace(String sellPlace) {
        this.sellPlace = sellPlace;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
}
