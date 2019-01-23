package com.xiaoshi.shop.mapper;

import com.xiaoshi.shop.bean.UserBean;

import java.util.List;


public interface UserMapper {
    /**
     * 查询所有的用户
     * @return 所有用户列表
     */
    List<UserBean> selectAllUser();
}
