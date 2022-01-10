package com.zhuquan.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 毕业生工作详细信息
 */
@Data

public class StudentWorkInfo {
    @Id
    private Integer userId;

    private String company;
    private String jobs;
    private Integer salary;
    private String companyCity;
    private String detailedAddress;
    private String industrey;
    private Integer threeNumber;
    private String resumeUrl;

}
