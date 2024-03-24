package com.Aaron.service.impl;

import com.Aaron.entity.User;
import com.Aaron.mapper.UserMapper;
import com.Aaron.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public String login(User user) {
        //  先与数据库中的数据比对，判断是否为真
        User info = userMapper.selectById(1);

        //  如果与数据库中的数据一致，则生成jwt token 返回给前端
        if (info.getAccount().equals(user.getAccount()) && info.getPassword().equals(user.getPassword())) {
            JwtBuilder jwtBuilder = Jwts.builder();

            long expiration = 1000*60*60*72;//设置过期时间，3天时间

            String jwtToken = jwtBuilder
                    .setHeaderParam("typ","JWT")
                    .setHeaderParam("alg","HS256")
                    .claim("account",user.getAccount())
                    .setExpiration(new Date(System.currentTimeMillis()+expiration))
                    .signWith(SignatureAlgorithm.HS256,"Aaron")
                    .compact();

            return jwtToken;
        }else{
            return null;
        }
    }
}
