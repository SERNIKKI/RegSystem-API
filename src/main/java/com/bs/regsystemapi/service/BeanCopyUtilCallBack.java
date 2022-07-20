package com.bs.regsystemapi.service;

/**
 * @AUTOR qpj
 * @Decription 函数式接口
 **/
@FunctionalInterface
public interface BeanCopyUtilCallBack<S,T> {
    /**
     * 接口方法
     * @param s
     * @param t
     */
    void copyCallBack(S s,T t);

}
