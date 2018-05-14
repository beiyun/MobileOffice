package com.beiyun.workers.enums;

/**
 * @author Administrator
 */

public enum Plant {

    PLANT_PROMISE(0,"种植承诺"),
    PLANT_APPLY(1,"种植申请"),
    PLANT_QUALIFICATION(2,"资格审查"),
    PRE_CONTRACT(3,"预签合同"),
    PLANT_COUNT(4,"清塘点株"),
    PLANT_CONTRACT(5,"种植合同"),
    CURE_RESERVATION(6,"烤烟预约"),
    BUY_RESERVATION(7,"收购预约");

    private int PlantType;
    private String PlantName;

    Plant(int plantType, String plantName) {
        PlantType = plantType;
        PlantName = plantName;
    }

    public int getPlantType() {
        return PlantType;
    }

    public String getPlantName() {
        return PlantName;
    }
}
