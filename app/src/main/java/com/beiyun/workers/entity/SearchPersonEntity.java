package com.beiyun.workers.entity;

public class SearchPersonEntity {

    private String personCompanyName;
    private String personName;
    private String personCall;

    public String getPersonCompanyName() {
        return personCompanyName;
    }

    public void setPersonCompanyName(String personCompanyName) {
        this.personCompanyName = personCompanyName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonCall() {
        return personCall;
    }

    public void setPersonCall(String personCall) {
        this.personCall = personCall;
    }

    @Override
    public String toString() {
        return "SearchPersonEntity{" +
                "personCompanyName='" + personCompanyName + '\'' +
                ", personName='" + personName + '\'' +
                ", personCall='" + personCall + '\'' +
                '}';
    }
}
