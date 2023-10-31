package com.ppyl.Caritas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by jpgm on 31/10/22.
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/home").setViewName("/home");
        registry.addViewController("/admin/login").setViewName("/admin/login");
        registry.addViewController("/admin/publicacion/new").setViewName("/admin/publicacionAdmin/new");
    }
}