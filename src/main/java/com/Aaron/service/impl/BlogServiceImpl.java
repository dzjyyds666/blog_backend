package com.Aaron.service.impl;

import com.Aaron.entity.Blog;
import com.Aaron.mapper.BlogMapper;
import com.Aaron.mapper.BtcontactMapper;
import com.Aaron.mapper.TypeMapper;
import com.Aaron.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private BtcontactMapper btcontactMapper;

    @Override
    public List<Blog> getBlogInfo() {
        List<Blog> list = blogMapper.getBlogInfo();
        return list;
    }

    @Override
    public String deleteBlog(List<Blog> blogList) {

        try {
            for (Blog blog : blogList) {
                //先修改type_number
                typeMapper.reduceTypeNumber(blog.getId());
                //删除btconnect中blog对应的type
                btcontactMapper.deleteByBlogId(blog.getId());
                blogMapper.deleteById(blog.getId());
            }
            return "删除成功";
        } catch (Exception e) {
            return "删除失败";
        }

    }

    @Override
    public String editBlog(Blog blog) {
        blogMapper.editBlog(blog);
        return "修改成功";
    }

    @Override
    public Blog getBlogById(Integer id) {
        return blogMapper.getgetBlogById(id);
    }

    @Override
    public List<Blog> postSelectBlog(Blog blog) {

        //TODO 迭代器
//        List<Blog> list = blogMapper.getBlogInfo();
//        Iterator<Blog> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            Blog blog1 = iterator.next();
//            if (blog.getTitle() != null && !blog1.getTitle().contains(blog.getTitle())) {
//                iterator.remove();
//            }
//            if (blog.getStatus() != null && !Objects.equals(blog.getStatus(), blog1.getStatus())) {
//                iterator.remove();
//            }
//            if (blog.getTag() != null && !Objects.equals(blog.getTag(), blog1.getTag())) {
//                iterator.remove();
//            }
//        }
//        return list;

        List<Blog> list = blogMapper.getBlogInfo();
        List<Blog> list1 = new ArrayList<>();
        for (Blog blog1 : list) {
            if (blog.getTitle() != null) {
                if (blog1.getTitle().contains(blog.getTitle())) {
                    System.out.println("标题匹配成功");
                } else continue;
            }
            if (blog.getTag() != null) {
                if (Objects.equals(blog1.getTag(), blog.getTag())) {
                    System.out.println("标签匹配成功");
                } else continue;
            }
            if (blog.getStatus() != null) {
                if (Objects.equals(blog1.getStatus(), blog.getStatus())) {
                    System.out.println("状态匹配成功");
                } else continue;
            }
            list1.add(blog1);
        }

        return list1;
    }


}
