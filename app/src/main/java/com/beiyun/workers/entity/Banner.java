package com.beiyun.workers.entity;

public class Banner {

    private String bannerUrl;
    private String bannerDetailUrl;
    private String explain;

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getBannerDetailUrl() {
        return bannerDetailUrl;
    }

    public void setBannerDetailUrl(String bannerDetailUrl) {
        this.bannerDetailUrl = bannerDetailUrl;
    }
}
