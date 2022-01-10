package com.zhuquan.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 请求中异常情况抛出，由全局异常处理类捕获，并处理返回提示给用户
 */
@Data
public class ExceptionResult extends RuntimeException implements Serializable {

    private HttpStatus status;
    private String msg;
    private Long timestamp;

    public static ExceptionResult build(HttpStatus status, String msg){
        ExceptionResult result = new ExceptionResult();
        result.setMsg(msg);
        result.setStatus(status);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }

}
