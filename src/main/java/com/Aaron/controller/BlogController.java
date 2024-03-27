package com.Aaron.controller;

import com.Aaron.entity.Blog;
import com.Aaron.service.IBlogService;
import com.Aaron.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 博客表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("/getBlog")
    public Result getBlogInfo(){

        List<Blog> list = blogService.getBlogInfo();

        return Result.Success();
    }

}
