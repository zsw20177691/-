package com.zhuquan.util;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private Long total;
    private List<T> data;

    // 用于存储其他参数
    private Object param;

    public PageResult(){
    }

    public PageResult(Long total, List<T> data){
        this.total = total;
        this.data = data;
    }

}
