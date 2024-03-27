package com.Aaron.controller;

import com.Aaron.entity.User;
import com.Aaron.service.IUserService;
import com.Aaron.utils.JwtToken;
import com.Aaron.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtToken jwtToken;


    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody User user) {
        String token = userService.login(user);
        if (token == null) {
            //账号或密码错误
            return ResponseEntity.status(201)
                    .body("账号或密码错误");
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("new-token", token);
        return ResponseEntity.status(200)
                .headers(httpHeaders)
                .body(token);
    }

    @GetMapping("/logout")
    public Result Logout(@RequestHeader("Authorization") String token) {
        String token1 = jwtToken.dealWithToken(token);
        userService.logout(token1);
        return Result.Success("注销成功", null);
    }

    @GetMapping("/getInfo")
    public Result getUserInfo() {
        User userInfo = userService.getUserInfo();
        return Result.Success(userInfo);
    }

    @PostMapping("/editinfo")
    public Result postEditInfo(@RequestBody User user) {
        userService.postEditInfo(user);
        return Result.Success("修改成功");
    }
}

