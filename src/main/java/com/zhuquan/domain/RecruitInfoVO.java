package com.zhuquan.domain;

import lombok.Data;

/***
 * @Date 2021/3/9 21:35
 * @Desc  前端查<招聘详情>时封装的数据
 */
@Data
public class RecruitInfoVO {

    private RecruitInfo recruit;    // 招聘具体信息

    private CompanyInfo company;    // 公司信息

}
