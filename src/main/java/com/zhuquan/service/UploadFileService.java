package com.zhuquan.service;


import com.zhuquan.domain.UploadFile;
import com.zhuquan.mapper.UploadFileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UploadFileService {

    @Resource
    private UploadFileMapper uploadFileMapper;

    public void saveUploadFile(UploadFile uploadFile){
        uploadFileMapper.insertSelective(uploadFile);
    }
}
