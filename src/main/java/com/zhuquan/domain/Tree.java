package com.zhuquan.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Data
public class Tree {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private Integer parentId;
    private String name;
    private Integer level;
    private Integer type;

    @Transient
    private List<Tree> children;
}
