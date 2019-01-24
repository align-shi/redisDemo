package com.xiaoshi.shop.service;

import com.xiaoshi.shop.bean.UserBean;

import java.util.List;

/**
 * @description: 用户行为的抽象类
 * @author: xiaopang
 * @create: 2019-01-24 14:00
 **/
public interface IUserService {
    /**
     * 查询所有的用户信息
     * @return 用户列表
     */
    List<UserBean> getAllUser();

    /**
     * 根据id查找用户
     * @return 用户信息
     */
    UserBean getUserById();

    /**
     * 根据用户名查找用户信息，看用户是否重复等
     * @return
     */
    UserBean getUserByName();


}
