package com.Aaron.service.impl;

import com.Aaron.entity.Blog;
import com.Aaron.mapper.BlogMapper;
import com.Aaron.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 博客表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> getBlogInfo() {
        List<Blog> list = blogMapper.getBlogInfo();
        return list;
    }
}
