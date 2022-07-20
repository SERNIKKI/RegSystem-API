package com.bs.regsystemapi.enumeration.common;

import java.lang.annotation.Native;

/**
 * @author qpj
 * @date 2022/3/23 21:48
 */
public class Action {

    /**
     * 更改
     */
    @Native public static final String UPDATE = "update";

    /**
     * 删除
     */
    @Native public static final String DELETE = "delete";

    /**
     * 恢复
     */
    @Native public static final String RECOVER = "recover";

    /**
     * 新增
     */
    @Native public static final String INSERT = "insert";

    /**
     * 批量更改
     */
    @Native public static final String BATCH_UPDATE = "batch_update";

    /**
     * 批量删除
     */
    @Native public static final String BATCH_DELETE = "batch_delete";

    /**
     * 批量恢复
     */
    @Native public static final String BATCH_RECOVER = "batch_recover";

    /**
     * 批量新增
     */
    @Native public static final String BATCH_INSERT = "batch_insert";

}
