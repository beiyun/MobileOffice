package com.beiyun.workers.interf;

/**
 * Created by zqht on 2016/6/29 14:07
 * Email:zmm534635184@sina.com
 */
public interface ISubsidyForm {
    String getProject();

    String getStandard();

    String getCash();

    void setCash(String cash);

    void setProject(String project);

    void setStandard(String standard);

    void setOnDeleteClickListener(OnDeleteClickListener deleteClickListener);
}
