package com.wak.dispatch.constant;

/**
 * 用户状态
 * Created by sophy
 */
public enum UserStatusEnum {

    ON("上线", 1),
    OFF("下线",2),
    FORBIDDEN("禁用", 3);

    private String name;

    private Integer value;

    UserStatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }


    public static UserStatusEnum getByName(String name){
        for(UserStatusEnum sortName : UserStatusEnum.values()){
            if(sortName.getName().equals(name)){
                return sortName;
            }
        }
        return null;
    }
}
