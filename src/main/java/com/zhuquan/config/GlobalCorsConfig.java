package com.zhuquan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Desc  允许指定ip域名跨域
 * @Date 2021/3/2 15:36
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter(){

        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://47.93.204.64");
        config.addAllowedOrigin("http://127.0.0.1");
        config.addAllowedOrigin("http://localhost");

        //允许发送cookie
        config.setAllowCredentials(true);
        //运行发送的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        //运行的头信息
        config.addAllowedHeader("*");
        //特殊请求的有效时长
        config.setMaxAge(3600L);

        //添加拦截的路径
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",config);

        return new CorsFilter(configurationSource);
    }
}
