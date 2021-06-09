package com.s1dmlgus.instagram02.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String uploadFolder;


    // WebMvcConfig 가 낚아채서 -> 주소 변경함함

   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/upload/**")       // jsp 페이지에서 /upload/** 이런 주소 패턴이 나오면 발동
                .addResourceLocations("file:///" + uploadFolder)
                .setCachePeriod(60 * 10 * 6)   // 초단위-> 60초 * 10개 * 6개
                .resourceChain(true)            // 발동
                .addResolver(new PathResourceResolver());

    }
}
