package com.zhuquan.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

/***
 * @Date 2021/3/9 16:27
 * @Desc  普通用户信息 及学生
 */
@Data
public class PersonalInfo {

    @Id
    private Integer userId;

    private String name;
    private Integer status;     // 求职状态
    private String company;

    private Integer sex;        // 1 男 0 女
    private String birthday;      // 生日

    private String school;      // 学校
    private String major;       // 专业
    private String education;   // 学历
    private String studyDate;   // 在校时间 2020-2021

    private String position;    // 岗位
    private Integer waves;      // 预期薪资
    private String describes;

    private String resumeUrl;   // 简历文件url
}
