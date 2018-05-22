package com.beiyun.workers.entity;

/**
 * Created by zqht on 2016/7/18 16:23
 * Email:zmm534635184@sina.com
 */
public class VersionInfo {

    /**
     * reason : success
     * resultCode : 100
     * versionCode : 2
     * versionName : v3.0.1
     * url : /apk/xxx.apk
     */

    private String reason;
    private int resultCode;
    private int versionCode;
    private String versionName;
    private String url;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "VersionInfo{" +
                "reason='" + reason + '\'' +
                ", resultCode=" + resultCode +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
