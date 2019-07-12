package com.learning.config;

import com.learning.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages={"com.learning"})
@Configuration
public class BeanConfig {

    @Bean(value = "user")
    public User user() {
        User user = new User();
        user.setName("天津银行");
        return user;
    }

}
