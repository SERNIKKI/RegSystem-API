package com.bs.regsystemapi.utils;

import lombok.Data;

@Data
public class ManagePageRequest extends BaseAuthCommand{
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 每页数量
     */
    private int pageSize = 10;
}
