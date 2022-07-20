package com.bs.regsystemapi.filter;

import com.alibaba.druid.util.StringUtils;
import com.bs.regsystemapi.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Date 2022/4/26 17:22
 */
@Component
@Slf4j
public class AuthInfoInterceptor implements org.springframework.web.servlet.HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        log.info(">>>>>>>拦截到api相关请求头<<<<<<<<"+token);
        if(!StringUtils.isEmpty(token)){
            //直接搂下来，放到ThreadLocal中 后续直接从中获取
            ThreadLocalUtils.set("Authorization",token);
        }
        return true; //注意 这里必须是true否则请求将就此终止。
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除Authorization
        ThreadLocalUtils.remove("Authorization");
        log.info("移除请求头中的app-user："+ThreadLocalUtils.get("Authorization"));
    }
}
