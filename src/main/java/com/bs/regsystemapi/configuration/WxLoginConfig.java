package com.bs.regsystemapi.configuration;

/**
 * Wx授权登录 配置类
 * @Date 2022/4/25 11:23
 */
public interface WxLoginConfig {

    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 你的appid
    public static final String WX_LOGIN_APPID = "wxf541e6405998a243";
    // 你的密匙
    public static final String WX_LOGIN_SECRET = "3670f42333ea7c88a7ce93e3ea56ca74";
    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

}
