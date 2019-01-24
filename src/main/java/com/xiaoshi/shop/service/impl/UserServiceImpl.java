package com.xiaoshi.shop.service.impl;

import com.xiaoshi.shop.bean.UserBean;
import com.xiaoshi.shop.mapper.UserMapper;
import com.xiaoshi.shop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户行为的具体实现类
 * @author: xiaopang
 * @create: 2019-01-24 14:01
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserBean> getAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public UserBean getUserById() {
        return null;
    }

    @Override
    public UserBean getUserByName() {
        return null;
    }
}
