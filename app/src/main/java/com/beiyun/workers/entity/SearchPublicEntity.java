package com.beiyun.workers.entity;

/**
 * @author beiyun
 */
public class SearchPublicEntity {

    private String publicCompanyName;
    private String publicTitle;
    private String publicTime;

    private String id; // ID

    private String name; // 名称

    private String years; // 年度

    private Integer types; // 公示类型 1资格审查、2合同预签、3清塘点株、4合同变更

    private String image; // 公示的图片

    private String province;// 省

    private String city;// 市

    private String county;// 县

    private String towns;// 乡镇

    private String village;// 村委会

    private String villageGroup;// 村小组

    private String userId; // 上传人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPublicCompanyName() {
        return publicCompanyName;
    }

    public void setPublicCompanyName(String publicCompanyName) {
        this.publicCompanyName = publicCompanyName;
    }

    public String getPublicTitle() {
        return publicTitle;
    }

    public void setPublicTitle(String publicTitle) {
        this.publicTitle = publicTitle;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    @Override
    public String toString() {
        return "SearchPublicEntity{" +
                "publicCompanyName='" + publicCompanyName + '\'' +
                ", publicTitle='" + publicTitle + '\'' +
                ", publicTime='" + publicTime + '\'' +
                '}';
    }
}
