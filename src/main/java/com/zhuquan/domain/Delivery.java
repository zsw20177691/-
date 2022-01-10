package com.zhuquan.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

/***
 * @Date 2021/3/14 14:48
 * @Desc  记录用户投递 简历
 */

@Data
public class Delivery {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private Integer userId;     // 投递简历的学生用户id
    private Integer companyId;  // 公司id
    private Integer recruitId;  // 岗位id

    private Integer status;     // 参考 StatusType
    private Date createDate;

    private String positionName;    // 岗位名称
    private String userName;        // 用户名
}
