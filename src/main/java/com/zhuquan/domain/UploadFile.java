package com.zhuquan.domain;

import com.zhuquan.enums.FileType;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

@Data
public class UploadFile {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String name;

    private String realPath;

    private String url;

    private Date createDate;

    private Integer type;        // FileType 枚举类


}
