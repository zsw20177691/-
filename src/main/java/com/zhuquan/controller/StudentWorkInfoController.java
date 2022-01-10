package com.zhuquan.controller;

import com.zhuquan.domain.StudentWorkInfo;
import com.zhuquan.domain.User;
import com.zhuquan.mapper.UserMapper;
import com.zhuquan.service.StudentWorkInfoservice;
import com.zhuquan.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/studentworkinfo")
public class StudentWorkInfoController {
    @Autowired
    private StudentWorkInfoservice studentWorkInfoservice;

    //保存毕业生用户的详细工作信息
    @RequestMapping("/editStudentworkInfo")
    public void editStudentworkInfo(StudentWorkInfo studentWorkInfo){
        User user= UserUtil.getUser();
        studentWorkInfo.setUserId(user.getId());
        studentWorkInfoservice.editStudenntWorkInfo(studentWorkInfo);
    }
    //毕业生修改工作详细信息
    @RequestMapping("/edit")
    public void editworkInfo(@RequestBody StudentWorkInfo studentWorkInfo){
        User user= UserUtil.getUser();
        studentWorkInfo.setUserId(user.getId());
        studentWorkInfoservice.editWorkInfo(studentWorkInfo);
    }
    @RequestMapping("/workInfo")
    public Object studentworkinfo(StudentWorkInfo studentWorkInfo){
        User user = UserUtil.getUser();
        studentWorkInfo.setUserId(user.getId());
         return studentWorkInfoservice.getWorkInfo(studentWorkInfo);

    }
}
