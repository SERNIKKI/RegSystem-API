package com.bs.regsystemapi.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description : 数据权限控制参数
 */
public class BaseAuthCommand implements Serializable {
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
