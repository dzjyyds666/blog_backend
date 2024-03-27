package com.Aaron.service;

import com.Aaron.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface IBlogService extends IService<Blog> {

    List<Blog> getBlogInfo();

    String deleteBlog(List<Blog> blogList);

    String editBlog(Blog blog);

    Blog getBlogById(Integer id);

    List<Blog> postSelectBlog(Blog blog);
}
