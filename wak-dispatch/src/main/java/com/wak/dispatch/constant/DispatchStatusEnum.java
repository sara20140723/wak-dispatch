package com.wak.dispatch.constant;

/**
 * 配送状态
 * Created by sophy
 */
public enum DispatchStatusEnum {

    RECEIVE_CUSTOMER_ORDER("接单", 1),
    PICK_PACKAGE("确认缺货",2),
    FINISH_DISPATCH("完成", 3),
    CANCEL_OF_DISPATCH("派送已取消", 4),
    CANCEL_OF_CUSTOMER("客户取消", 5);

    private String name;

    private Integer value;

    DispatchStatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }


    public static DispatchStatusEnum getByName(String name){
        for(DispatchStatusEnum sortName : DispatchStatusEnum.values()){
            if(sortName.getName().equals(name)){
                return sortName;
            }
        }
        return null;
    }
}
