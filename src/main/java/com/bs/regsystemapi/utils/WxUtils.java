package com.bs.regsystemapi.utils;

import com.alibaba.fastjson.JSONObject;
import com.bs.regsystemapi.configuration.WxLoginConfig;
import com.wybusy.EasyHttp;

/**
 * Wx工具类
 * @Date 2022/4/26 15:21
 */
public class WxUtils {
    public static JSONObject getWxResult(String code) {
        // 发送请求 + 配置请求参数
        String urlPath = WxLoginConfig.WX_LOGIN_URL + "?appid=" + WxLoginConfig.WX_LOGIN_APPID + "&secret="
                + WxLoginConfig.WX_LOGIN_SECRET + "&js_code=" + code + "&grant_type=" + WxLoginConfig.WX_LOGIN_GRANT_TYPE;
        String wxResult = EasyHttp.get(urlPath, null);
        return JSONObject.parseObject(wxResult);
    }
}
