package com.bjpowernode.front.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    /*处理跨域，实现全局跨域处理*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //WebMvcConfigurer.super.addCorsMappings(registry);
        System.out.println("==========addCorsMappings==========");
        //addMapping,处理的请求地址，拦截这些地址，使用跨域处理逻辑
        //allowedOriginPatterns,可跨域的域名，可以为*
        //allowedMethods,支持跨域请求的http的方式
        //allowCredentials,true代表支持发送cookie
        //maxAge,单位是秒，此处为3600秒，在有效期间，不用发出另一条预检请求
        //allowedHeaders,支持跨域的请求头，在请求头中包含那些数据的时候，可以支持跨域功能

        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:8082")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
