package com.Aaron.service;

import com.Aaron.entity.Blog;
import com.Aaron.utils.Year;
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

    String postAddBlog(Blog blog);

    List<Blog> getBlogInfoPage(Integer offset, Integer limit);

    Integer getBlogNum();

    List<Blog> getSearch(String search);

    List<Year> getArchive();

    List<Blog> getArchiveBlog();

    Blog getBlogDetail(Integer id);

    List<Blog> getByTypeId(Integer id,Integer current,Integer pagesize);
}
