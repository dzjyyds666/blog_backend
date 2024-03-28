package com.Aaron.controller;

import com.Aaron.entity.Blog;
import com.Aaron.service.IBlogService;
import com.Aaron.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result getBlogInfo() {

        List<Blog> list = blogService.getBlogInfo();
        return Result.Success(list);
    }

    @PostMapping("/deleteBlog")
    public Result deleteBlog(@RequestBody List<Blog> blogList) {
        String message = blogService.deleteBlog(blogList);
        return Result.Success(message);
    }

    @PostMapping("/editBlog")
    public Result editBlog(@RequestBody Blog blog) {

        String message = blogService.editBlog(blog);

        return Result.Success(message);
    }

    @GetMapping("/getById")
    public Result getBlogById(Integer id) {
        Blog blog = blogService.getBlogById(id);
        return Result.Success(blog);
    }

    @PostMapping("/searchBlog")
    public Result postSelectBlog(@RequestBody Blog blog){

        List<Blog> list = blogService.postSelectBlog(blog);
        return Result.Success(list);
    }
}
