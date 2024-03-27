package com.Aaron.mapper;

import com.Aaron.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
}
