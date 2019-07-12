package com.learning.service;

@FunctionalInterface
public interface IUserService {

    public void setMessage(String message);

    default void defaultMethod() {
        System.out.println("IUserService -- 默认方法！");
    }

}
