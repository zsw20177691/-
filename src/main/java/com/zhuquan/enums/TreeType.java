package com.zhuquan.enums;

import lombok.Getter;

public enum TreeType {


    POSITION(1, "岗位", 3),
    INDUSTRY(2,"行业", 2),
    AREA(3,"地区", 2),
    ;

    @Getter
    private Integer value;
    @Getter
    private String desc;
    @Getter
    private Integer maxDepth;

    TreeType(Integer value, String desc, Integer maxDepth){
        this.value = value;
        this.desc = desc;
        this.maxDepth = maxDepth;
    }

    /**
     *  通过值获取对应的TreeType
     * @param value
     * @return
     */
    public static TreeType getTreeType(Integer value){
        TreeType[] values = TreeType.values();
        for (TreeType val : values){
            if (val.value.equals(value)){
                return val;
            }
        }
        return null;
    }
}
