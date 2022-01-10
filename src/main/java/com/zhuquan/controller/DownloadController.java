package com.zhuquan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class DownloadController {

    /**
     * @Desc  保证下载时的名字是上传时的名字，防止下的文件名uuid
     * @Date 2021/3/4 21:29
     */
    @RequestMapping("/file/**/filename/*")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         *  将文件虚拟路径导向真实路径
         * /file/3/6/9/066d3bf831434fefbe99bdd910fda8dd.jpg/filename/xxxxxx.jpg
         *   变成
         * /file/3/6/9/066d3bf831434fefbe99bdd910fda8dd.jpg
         */
        String path = "/" + request.getRequestURI().substring(1, request.getRequestURI().indexOf("/filename"));
        request.getRequestDispatcher(path).forward(request,response);

    }

}

