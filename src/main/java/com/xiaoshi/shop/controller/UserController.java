package com.xiaoshi.shop.controller;

import com.xiaoshi.shop.bean.UserBean;
import com.xiaoshi.shop.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 关于用户的行为
 * @author: xiaopang
 * @create: 2019-01-23 19:37
 **/
@RestController
@RequestMapping("user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @RequestMapping("/getUser")
    public List<UserBean> getUser() {
        List<UserBean> userList = userService.getAllUser();
        return userList;
    }
}
