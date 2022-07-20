package com.bs.regsystemapi.configuration;

import com.bs.regsystemapi.filter.AuthInfoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author qpj
 * @Date: 2022/02/17/ 14:57
 * @Description
 */
@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
    @Autowired
    private AuthInfoInterceptor authInfoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInfoInterceptor)
                .addPathPatterns("/**/patient/**")
                .addPathPatterns("/**/patPerson/**")
                .addPathPatterns("/**/patPayRecord/**")
                .addPathPatterns("/**/patRegRecord/**")
                .addPathPatterns("/**/patAttention/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        /**
         * 所有请求都允许跨域，使用这种配置就不需要
         * 在interceptor中配置header了
         */
        corsRegistry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }


}
