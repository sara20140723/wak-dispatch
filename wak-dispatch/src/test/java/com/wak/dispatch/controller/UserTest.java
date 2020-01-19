package com.wak.dispatch.controller;

import com.alibaba.fastjson.JSONObject;
import com.wak.dispatch.model.dto.UserDto;
import com.wak.dispatch.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        System.out.println(111);
    }

}