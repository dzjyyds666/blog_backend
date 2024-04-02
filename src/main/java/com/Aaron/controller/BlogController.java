package com.Aaron.controller;

import com.Aaron.entity.Blog;
import com.Aaron.service.IBlogService;
import com.Aaron.utils.Result;
import com.Aaron.utils.Year;
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


    @PostMapping("/addBlog")
    public Result postAddBlog(@RequestBody Blog blog) {
        String message = blogService.postAddBlog(blog);
        if (message.contains("失败")) {
            return Result.fail(201, message);
        } else {
            return Result.Success(200, message, null);
        }
    }

    @GetMapping("/getById")
    public Result getBlogById(Integer id) {
        Blog blog = blogService.getBlogById(id);
        return Result.Success(blog);
    }

    @PostMapping("/searchBlog")
    public Result postSelectBlog(@RequestBody Blog blog) {

        List<Blog> list = blogService.postSelectBlog(blog);
        return Result.Success(list);
    }

    @GetMapping("/front/getBlogNum")
    public Result getBlogNum() {
        Integer number = blogService.getBlogNum();
        return Result.Success(number);
    }

    @GetMapping("/front/getBlogPage")
    public Result getBlogInfoPage(Integer offset, Integer limit) {

        List<Blog> list = blogService.getBlogInfoPage(offset, limit);
        return Result.Success(list);
    }

    @GetMapping("/front/search")
    public Result getSearch(String search) {
        List<Blog> list = blogService.getSearch(search);

        if (list.isEmpty()) {
            return Result.fail(201, "博客没有匹配项");
        }

        return Result.Success("博客查询成功", list);
    }

    @GetMapping("/front/archive")
    public Result getArchive() {
        List<Year> list = blogService.getArchive();
        return Result.Success(list);
    }

    @GetMapping("/front/archiveBlog")
    public Result getArchiveBlog() {
        List<Blog> list = blogService.getArchiveBlog();
        return Result.Success(list);
    }

    @GetMapping("/front/blogDetail")
    public Result getBlogDetail(Integer id) {
        Blog blog = blogService.getBlogDetail(id);
        return Result.Success(blog);
    }

    @GetMapping("/front/getByTypeId")
    public Result getByTypeId(Integer id,Integer current,Integer pagesize) {
        List<Blog> blogList = blogService.getByTypeId(id,current,pagesize);
        return Result.Success(blogList);
    }
}
