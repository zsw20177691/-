package com.zhuquan.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

@Data
public class RecruitInfo {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer companyId;      // 公司id

    private String position;        // 岗位
    private String positionName;    // 岗位名称
    private String tag;             // 标签
    private Integer wavesBegin;     // 薪资 k  起薪资
    private Integer wavesEnd;       // 最高薪资
    private String describes;       // 描述
    private String education;       // 学历
    private Integer status;
    private Date createDate;

}
