package com.zhuquan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String name;
    private Integer status;     // 参考枚举statusType
    private Integer type;       // 参考枚举userType
    private String avatar;      // 头像


}
