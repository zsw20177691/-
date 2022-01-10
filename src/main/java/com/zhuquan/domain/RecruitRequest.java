package com.zhuquan.domain;

import lombok.Data;

/***
 * @Date 2021/3/9 22:57
 * @Desc 用于 传递前端 查询招聘信息的请求
 */
@Data
public class RecruitRequest {

    private String keyword;         // 岗位名称

    private Integer wavesBegin;     // 薪资 k  起薪资
    private Integer wavesEnd;       // 最高薪资
    private String position;        //


    private String industry;        // 行业
    private String scale;           // 人员规模
    private String address;         // 市区地址

    private Integer status;

}
