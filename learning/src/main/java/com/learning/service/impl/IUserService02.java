package com.learning.service.impl;

public interface IUserService02 {

    default void defaultMethod() {
        System.out.println("IUserService02 -- 默认方法！");
    }

}
