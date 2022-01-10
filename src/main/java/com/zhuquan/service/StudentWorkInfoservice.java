package com.zhuquan.service;

import com.zhuquan.domain.StudentWorkInfo;
import com.zhuquan.mapper.StudenntWorkInfoMapper;
import com.zhuquan.util.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Service
public class StudentWorkInfoservice {
    @Resource
    private StudenntWorkInfoMapper studenntWorkInfoMapper;
    public StudentWorkInfo selectByUserId(Integer userId){
        StudentWorkInfo studentWorkInfo=new StudentWorkInfo();
        studentWorkInfo.setUserId(userId);
        return studenntWorkInfoMapper.selectOne(studentWorkInfo);
    }
    private void checkUserId(Integer userId){
        if (selectByUserId(userId)!=null){
            throw ExceptionResult.build(HttpStatus.BAD_REQUEST, "用户信息已经被保存，如需修改请点击修改，进行修改");

        }
    }
    @Transactional
    public void editStudenntWorkInfo(StudentWorkInfo studentWorkInfo){
        //校验用户信息
        checkUserId(studentWorkInfo.getUserId());
        studenntWorkInfoMapper.insert(studentWorkInfo);
    }
    public void editWorkInfo(StudentWorkInfo studentWorkInfo){
        studenntWorkInfoMapper.updateByPrimaryKeySelective(studentWorkInfo);
    }
    public Object getWorkInfo(StudentWorkInfo studentWorkInfo){
        return studenntWorkInfoMapper.selectByPrimaryKey(studentWorkInfo.getUserId());
    }
}
