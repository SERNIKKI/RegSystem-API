package com.bs.regsystemapi.configuration;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author qpj
 * @Date: 2022/02/10/ 14:32
 * @Description
 */
/**
 * Sa-Token 权限认证 配置类
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    // 注册 Sa-Token 全局过滤器
//    @Bean
//    public SaServletFilter getSaServletFilter() {
//        return new SaServletFilter()
//                .addInclude("/**")
//                .addExclude("/favicon.ico","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**" ,"/doc.html/**","/error","/user/register","/user/login","/user/loginByRemember")
//                .setAuth(obj -> {
//                    // 校验 Id-Token 身份凭证     —— 以下两句代码可简化为：SaIdUtil.checkCurrentRequestToken()
//                    StpUtil.isLogin();
//                })
//                .setError(e -> {
//                    return SaResult.error("manage子服务当前的异常"+e.getMessage());
//                })
//                ;
//    }
    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("sa-token拦截器");
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**")
                // 排除外部调用接口
                .excludePathPatterns("/**/outer/**")
                // 排除指定url 排除企业logo 获取的方法
                .excludePathPatterns("/**/user/login/**", "/**/user/logout/**", "/**/error/**",
                        "/**/user/register/**", "/**/verify/**", "/**/monitorLogin/**", "/**/enterprise/get", "/**/enterprise/getLogo","/**/getSAStoken/**",
                        "/**/user/register","/**/user/login","/**/user/loginByRemember")
                .excludePathPatterns("/doc.html")
                // 排除swagger相关
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");;
    }
}



