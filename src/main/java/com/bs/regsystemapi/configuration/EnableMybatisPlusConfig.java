package com.bs.regsystemapi.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableMybatisPlusConfig
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MybatisPlusConfig.class)
public @interface EnableMybatisPlusConfig {
}
