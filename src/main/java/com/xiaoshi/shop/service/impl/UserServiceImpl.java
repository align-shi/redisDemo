package com.xiaoshi.shop.service.impl;

import com.xiaoshi.shop.bean.UserBean;
import com.xiaoshi.shop.mapper.UserMapper;
import com.xiaoshi.shop.service.IUserService;
import com.xiaoshi.shop.utils.RedisUtil;
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
    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<UserBean> getAllUser() {
        //首先看redis中是否有缓存
        List<UserBean> list = (List<UserBean>)redisUtil.get("userList");
        if (list != null){
            return list;
        }else {
            //如果没有在库中查出来之后进行缓存
            List<UserBean> listFromDB = userMapper.selectAllUser();
            redisUtil.set("userList", listFromDB);
            return listFromDB;
        }
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
