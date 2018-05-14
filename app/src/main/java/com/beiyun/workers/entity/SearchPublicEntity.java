package com.beiyun.workers.entity;

/**
 * @author beiyun
 */
public class SearchPublicEntity {

    private String publicCompanyName;
    private String publicTitle;
    private String publicTime;

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
