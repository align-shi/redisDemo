package com.xiaoshi.shop.mapper;

import com.xiaoshi.shop.bean.UserBean;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 用户行为映射方法
 */
public interface UserMapper {
    /**
     * 查询所有的用户
     * @return 所有用户列表
     */
    @Cacheable(value = "user",key = "'userlist'")
    List<UserBean> selectAllUser();
}
