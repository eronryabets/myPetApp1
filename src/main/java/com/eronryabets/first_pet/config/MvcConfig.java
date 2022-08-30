package com.eronryabets.first_pet.config;

import com.eronryabets.first_pet.utility.RedirectInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${download.path}")
    private String folderPath;
    @Value("${financeReports.path}")
    private String financeReports;
    @Value("${logs.path}")
    private String logsPath;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + uploadPath + "/");
        registry.addResourceHandler("/download/**")
                .addResourceLocations("file:" + folderPath + "/");
        registry.addResourceHandler("/user/logsAuth")
                .addResourceLocations("file:" + logsPath + "/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RedirectInterceptor());
    }
}