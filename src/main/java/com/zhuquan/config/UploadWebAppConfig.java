package com.zhuquan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * @Desc  将下载至本地的文件，发布到服务器，提供下载
 * @Date 2021/3/4 20:07
 */
@Configuration
public class UploadWebAppConfig implements WebMvcConfigurer {

    @Value("${recruit.upload.img}")
    private String locationsImg;

    @Value("${recruit.upload.file}")
    private String locationsFile;

    @Value("${recruit.download.img}")
    private String imgServiceUrl;

    @Value("${recruit.download.file}")
    private String fileServiceUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/"+imgServiceUrl+"/**").addResourceLocations("file:" + locationsImg);
        registry.addResourceHandler("/"+fileServiceUrl+"/**").addResourceLocations("file:" + locationsFile);
    }

}
