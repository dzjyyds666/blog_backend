package com.Aaron.mapper;

import com.Aaron.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;



import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 博客表 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface BlogMapper extends BaseMapper<Blog> {

    List<Blog> getBlogInfo();

    void editBlog(Blog blog);

    Blog getgetBlogById(Integer id);


    void postAddBlog(Blog blog);

    Integer selectNewBlog(String title);


    Integer getBlogNum();

    List<Blog> getSearch(String search);


    List<LocalDateTime> getYear();

    List<Blog> getblog();

    Blog getBlogDetail(Integer id);

    void updateCommentNum(Integer blogId);

    List<Blog> getByTypeId(Integer id);

    List<Blog> getFrontBlogInfo();
}

