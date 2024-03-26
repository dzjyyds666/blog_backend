package com.Aaron.service;

import com.Aaron.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface IUserService extends IService<User> {

    String login(User user);

    void logout(String token);

    User getUserInfo();

}
