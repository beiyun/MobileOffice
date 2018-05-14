package com.beiyun.workers.okhttp.helper;

/**
 * Created by zqht on 2016/8/30 14:45
 * Email:zmm534635184@sina.com
 */
public class ResultData {
    private String moduleID;//当前表单存于服务器的id用于远程签名
    private int resultCode;
    private String reason;
    private String verifyCode;
    private String did;
    private int status;//0,1
    private String msg;//'success' '系统错误'

    @Override
    public String toString() {
        return "ResultData{" +
                "did='" + did + '\'' +
                ", moduleID='" + moduleID + '\'' +
                ", resultCode=" + resultCode +
                ", reason='" + reason + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
