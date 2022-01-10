package com.zhuquan.enums;

import lombok.Getter;

public enum UserType {

    ADMIN(0, "管理员"),
    STUDENT(1,"学生"),
    COMPANY(2,"企业")
    ;

    @Getter
    private Integer value;
    @Getter
    private String desc;

    UserType(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    /**
     *  通过值获取对应的UserType
     * @param value
     * @return
     */
    public static UserType getUserType(Integer value){
        UserType[] values = UserType.values();
        for (UserType val : values){
            if (val.value.equals(value)){
                return val;
            }
        }
        return null;
    }

}
