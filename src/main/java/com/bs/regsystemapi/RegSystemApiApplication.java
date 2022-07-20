package com.bs.regsystemapi;

import cn.dev33.satoken.SaManager;
import com.bs.regsystemapi.configuration.EnableMybatisPlusConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableMybatisPlusConfig
@SpringBootApplication
@EnableAsync
public class RegSystemApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegSystemApiApplication.class, args);
        System.out.println("启动成功：Sa-Token配置如下" + SaManager.getConfig());
    }

}
