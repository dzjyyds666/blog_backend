package com.Aaron.controller;

import com.Aaron.entity.User;
import com.Aaron.service.IUserService;
import com.Aaron.utils.JwtToken;
import com.Aaron.utils.Result;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtToken jwtToken;

    @PostMapping("/login")
    public Result Login(@RequestBody User user) {
        String token = userService.login(user);
        if (token == null) {
            //账号或密码错误
            return Result.fail("账号或密码错误，请重新输入");
        }
        return Result.Success("登录成功", token);
    }

    @PostMapping("/logout")
    public Result Logout(@RequestHeader("Authorization") String token) {

        userService.logout(token);
        return Result.Success("注销成功", null);
    }

    @GetMapping("/getInfo")
    public Result getUserInfo(@RequestHeader("Authorization") String token){

        System.out.println(token);

        token = jwtToken.dealWithToken(token);

        System.out.println(token);
        String JwtToken = jwtToken.parseToken(token);
        if(Objects.equals(JwtToken, "true")) {
            User user = userService.getUserInfo();
            return Result.Success(null, user);
        } else if (Objects.equals(JwtToken,"false")) {
            return Result.fail(201, "token过期，请重新登录", null);
        }else{
            User user = userService.getUserInfo();
            return Result.Success(JwtToken,user);
        }
    }


}
