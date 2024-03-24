package com.Aaron.controller;

import com.Aaron.entity.User;
import com.Aaron.service.IUserService;
import com.Aaron.service.impl.UserServiceImpl;
import com.Aaron.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @PostMapping("/login")
    public Result Login(@RequestBody User user){

        String token = userService.login(user);

        if (token == null) {
            //账号或密码错误
            return Result.fail("账号或密码错误，请重新输入");
        }

        return Result.Success("登录成功",token);
    }

}
