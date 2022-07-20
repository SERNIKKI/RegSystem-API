package com.bs.regsystemapi.entity;

/**
 * @author qpj
 * @date 2022/3/1 14:32
 */
public class Actor<T> {
    private T actor;
    public T getActor() {
        return actor;
    }
    public void setActor(T actor) {
        this.actor = actor;
    }
    public Actor(T actor) {
        this.actor = actor;
    }
}
