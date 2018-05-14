package com.beiyun.workers.entity;

public class SearchPlantEntity {

    private String plantName;
    private String plantCall;


    @Override
    public String toString() {
        return "SearchPlantEntity{" +
                "plantName='" + plantName + '\'' +
                ", plantCall='" + plantCall + '\'' +
                '}';
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantCall() {
        return plantCall;
    }

    public void setPlantCall(String plantCall) {
        this.plantCall = plantCall;
    }
}
