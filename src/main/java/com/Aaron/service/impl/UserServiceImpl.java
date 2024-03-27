package com.Aaron.service.impl;

import com.Aaron.entity.User;
import com.Aaron.mapper.UserMapper;
import com.Aaron.service.IUserService;
import com.Aaron.utils.JwtToken;
import com.Aaron.utils.MyRedis;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


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
    private MyRedis myRedis;

    @Override
    public String login(User user) {

        //先对密码进行md5加密处理
        String md5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5);

        //  与数据库中的数据比对，判断是否为真
        User info = userMapper.selectById(1);
        //  如果与数据库中的数据一致，则生成jwt token 返回给前端 并把token存入redis
        if (info.getAccount().equals(user.getAccount()) && info.getPassword().equals(user.getPassword())) {
            JwtToken jwtToken = new JwtToken();
            String token = jwtToken.createJwt(user.getAccount());
            myRedis.setValue(info.getAccount()+" token",token);
            return token;
        }else{
            return null;
        }
    }

    @Override
    public void logout(String token) {
        Claims claims = Jwts.parser().setSigningKey("Aaron").parseClaimsJws(token).getBody();
        myRedis.deleteKey(claims.get("account").toString()+" token");
    }

    @Override
    public User getUserInfo() {
        return userMapper.selectById(1);
    }

    @Override
    public void postEditInfo(User user) {

        //对密码进行md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        userMapper.postEditInfo(user);
    }
}
