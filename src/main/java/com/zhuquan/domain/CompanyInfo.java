package com.zhuquan.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Transient;

/***
 * @Date 2021/3/9 16:28
 * @Desc  企业用户信息
 */
@Data
public class CompanyInfo {

    @Id
    private Integer userId;

    private String name;
    private String industry;        // 行业
    private String fullName;        // 全称
    private String scale;           // 人员规模
    private String address;         // 市区地址
    private String detailAddress;   // 详细地址
    private String describes;       // 简述

}
