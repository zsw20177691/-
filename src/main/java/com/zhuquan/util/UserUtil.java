package com.zhuquan.util;

import com.zhuquan.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class UserUtil {

    /***
     * @Date 2021/3/14 15:28
     * @Desc  获取当前用户的用户信息
     * 通过servlet方法获取登录用户信息
     */
    public static User getUser(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        User user = (User) requestAttributes.getAttribute("user",RequestAttributes.SCOPE_SESSION);
        if (user == null){
            throw ExceptionResult.build(HttpStatus.FORBIDDEN, "请先登录");
        }
        return user;
    }

    /***
     * @Date 2021/3/14 15:29
     * @Desc  向session作用域中填充user用户信息
     */
    public static void setUser( User user){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        requestAttributes.setAttribute("user", user, RequestAttributes.SCOPE_SESSION);
    }
}
