package com.learning.service.impl;

import com.learning.service.IUserService;

public class UserService implements IUserService, IUserService02 {

    @Override
    public void setMessage(String message) {

    }

    @Override
    public void defaultMethod() {
        IUserService02.super.defaultMethod();
    }

}
