package com.zhuquan.interceptor;


import com.zhuquan.util.ExceptionResult;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 *  全局异常处理类，捕获访问接口抛出的异常
 */
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * 捕获ExceptionResult异常，并将异常中的 响应码、提示、时间戳返回显示
     * @param e
     * @return
     */
    @ExceptionHandler(ExceptionResult.class)
    public ResponseEntity<Map> Exception(ExceptionResult e){
        Map<String, Object> map = new HashMap<>();
        map.put("status",e.getStatus().value());
        map.put("msg",e.getMsg());
        map.put("timestamp",e.getTimestamp());
        return ResponseEntity.status(e.getStatus()).body(map);

    }

}

