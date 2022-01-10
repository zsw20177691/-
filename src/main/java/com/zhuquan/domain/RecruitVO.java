package com.zhuquan.domain;

import lombok.Data;

/***
 * @Date 2021/3/9 21:35
 * @Desc  前端查询<招聘列表>时封装返回
 */
@Data
public class RecruitVO {

    // recruitInfo 数据
    private Integer id;
    private String positionName;    // 岗位名称
    private Integer wavesBegin;     // 薪资 k  起薪资
    private Integer wavesEnd;       // 最高薪资
    private String education;       // 学历

    // CompanyInfo 数据
    private String industry;        // 行业
    private String address;         // 市区地址

    // User 数据
    private String companyName;            // 公司名称简称
    private String avatar;          // 公司头像

}
