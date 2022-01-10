package com.zhuquan.enums;

import lombok.Getter;

public enum  FileType {

    IMG(0,"图片"),
    FILE(1,"普通文件")
    ;

    @Getter
    private Integer value;
    @Getter
    private String desc;

    FileType(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    /**
     *  通过值获取对应的FileType
     * @param value
     * @return
     */
    public static FileType getFileType(Integer value){
        FileType[] values = FileType.values();
        for (FileType val : values){
            if (val.value.equals(value)){
                return val;
            }
        }
        return null;
    }

}
