package com.Aaron.mapper;

import com.Aaron.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface UserMapper extends BaseMapper<User> {

    void postEditInfo(User user);
}
