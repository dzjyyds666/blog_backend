package com.Aaron.service.impl;

import com.Aaron.entity.User;
import com.Aaron.mapper.TokenblacklistMapper;
import com.Aaron.mapper.UserMapper;
import com.Aaron.service.IUserService;
import com.Aaron.utils.JwtToken;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenblacklistMapper tokenblacklistMapper;

    @Override
    public String login(User user) {
        //  先与数据库中的数据比对，判断是否为真
        User info = userMapper.selectById(1);
        //  如果与数据库中的数据一致，则生成jwt token 返回给前端
        if (info.getAccount().equals(user.getAccount()) && info.getPassword().equals(user.getPassword())) {
            JwtToken jwtToken = new JwtToken();
            return jwtToken.createJwt(user.getAccount());
        }else{
            return null;
        }
    }

    @Override
    public void logout(String token) {
        tokenblacklistMapper.insertToken(token);
    }
}
