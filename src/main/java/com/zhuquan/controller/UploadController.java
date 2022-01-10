package com.zhuquan.controller;


import com.zhuquan.domain.UploadFile;
import com.zhuquan.enums.FileType;
import com.zhuquan.service.UploadFileService;
import com.zhuquan.util.ExceptionResult;
import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${recruit.upload.img}")
    private String locationsImg;

    @Value("${recruit.upload.file}")
    private String locationsFile;

    @Value("${recruit.download.img}")
    private String imgServiceUrl;

    @Value("${recruit.download.file}")
    private String fileServiceUrl;

    @Value("${recruit.domainName}")
    private String domainName;

    // 上传图片符合文件后缀
    private List<String> accord = Arrays.asList("png", "jpg", "gif", "jpeg", "bmp");

    @Autowired
    private UploadFileService uploadFileService;

    /***
     * @Desc  上传图片
     * @Date 2021/3/4 19:39
     */
    @RequestMapping("/img")
    public Map<String, Object> imgUpload(@RequestParam("file") MultipartFile file) {

        // 后缀校验
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!accord.contains(ext)){
            throw ExceptionResult.build(HttpStatus.BAD_REQUEST, "上传图片格式错误");
        }

        UploadFile upload = upload(file, FileType.IMG);
        uploadFileService.saveUploadFile(upload);

        Map<String, Object> map = new HashMap<>();
        map.put("link", upload.getUrl());
        return map;
    }

    /***
     * @Desc  上传文件
     * @Date 2021/3/4 22:03
     */
    @RequestMapping("/file")
    public Map<String, Object> fileUpload(@RequestParam("file") MultipartFile file){

        UploadFile upload = upload(file, FileType.FILE);
        upload.setUrl(upload.getUrl()+"/filename/"+upload.getName());   // 此步可以在downloadController中进行一次转发，保证下载时文件名是上传的文件名
        uploadFileService.saveUploadFile(upload);

        Map<String, Object> map = new HashMap<>();
        map.put("link", upload.getUrl());
        return map;
    }

    /***
     * @Desc  保存文件至本地, 并返回 uploadFile
     * @Date 2021/3/2 15:30
     */
    private UploadFile upload(MultipartFile file, FileType type) {
        // 生成随机文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Random random = new Random();
        // 生成随机路径     2/5/9
        String dirPath = random.nextInt(10) + "/" + random.nextInt(10) + "/" + random.nextInt(10) + "/";

        // 创建本地路径
        String realPath = (type == FileType.FILE ? locationsFile : locationsImg)  + dirPath;

        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        //创建真实储存路径
        String path = realPath + fileName;
        try {
            file.transferTo(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionResult.build(HttpStatus.INTERNAL_SERVER_ERROR, "文件保存失败");
        }

        // 创建网络路径
        String webPath = "http://"+ domainName + "/"+ (type == FileType.FILE ? fileServiceUrl : imgServiceUrl) +"/" + dirPath +fileName;

        UploadFile uploadFile = new UploadFile();
        uploadFile.setCreateDate(new Date());
        uploadFile.setName(file.getOriginalFilename());
        uploadFile.setType(type.getValue());
        uploadFile.setUrl(webPath);
        uploadFile.setRealPath(path);
        return uploadFile;
    }



}
