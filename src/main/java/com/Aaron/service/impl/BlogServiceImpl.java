package com.Aaron.service.impl;

import com.Aaron.entity.Blog;
import com.Aaron.entity.Btcontact;
import com.Aaron.mapper.BlogMapper;
import com.Aaron.mapper.BtcontactMapper;
import com.Aaron.mapper.CommentsMapper;
import com.Aaron.mapper.TypeMapper;
import com.Aaron.service.IBlogService;
import com.Aaron.utils.Year;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public List<Blog> getBlogInfo() {
        return blogMapper.getBlogInfo();
    }

    @Override
    public String deleteBlog(List<Blog> blogList) {

        try {
            for (Blog blog : blogList) {
                //先修改type_number
                typeMapper.reduceTypeNumber(blog.getId());
                //删除btconnect中blog对应的type
                btcontactMapper.deleteByBlogId(blog.getId());
                //删除评论表中对应的评论
                commentsMapper.deleteComments(blog.getId());

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

    @Override
    public String postAddBlog(Blog blog) {
        try {
            //先插入博客数据
            blogMapper.postAddBlog(blog);
            //查询博客id
            Integer id = blogMapper.selectNewBlog(blog.getTitle());
            //在btconnect中插入博客与分类的关系
            for (String typeName : blog.getTypeNameList()) {
                //在type中获取type_id
                Integer typeId = typeMapper.selectIdByName(typeName);
                Btcontact btcontact = new Btcontact(null, id, typeId);
                btcontactMapper.insert(btcontact);
                //修改blog_number
                typeMapper.addTypeBlogNumber(typeId);
            }
            return "添加成功";
        } catch (Exception e) {
            return "文章标题重复";
        }
    }

    @Override
    public List<Blog> getBlogInfoPage(Integer offset, Integer limit) {
        List<Blog> list1 = new ArrayList<>();
        try {
            List<Blog> list = blogMapper.getBlogInfo();
            int start = (offset - 1) * limit;
            for (int i = 0; i < start + limit; i++) {
                if (i >= start) {
                    if (list.get(i) != null) {
                        list1.add(list.get(i));
                    }
                }
            }
            return list1;
        } catch (Exception e) {
            return list1;
        }
    }

    @Override
    public Integer getBlogNum() {
        return blogMapper.getBlogNum();
    }

    @Override
    public List<Blog> getSearch(String search) {

        return blogMapper.getSearch(search);

    }

    @Override
    public List<Year> getArchive() {
        List<LocalDateTime> year = blogMapper.getYear();
        List<Integer> list = new ArrayList<>();
        for (LocalDateTime time : year) {
            if (!list.contains(time.getYear())) {
                list.add(time.getYear());
            }
        }

        Comparator<Integer> reverseOrder = Collections.reverseOrder();
        list.sort(reverseOrder);
        List<Year> list1 = new ArrayList<>();

        for (Integer y : list) {
            int number = 0;
            for (LocalDateTime time : year) {
                if (time.getYear() == y) {
                    number++;
                }
            }
            Year newYear = new Year(y, number);
            list1.add(newYear);
        }

        return list1;
    }

    @Override
    public List<Blog> getArchiveBlog() {

        return blogMapper.getblog();
    }

    @Override
    public Blog getBlogDetail(Integer id) {
        return blogMapper.getBlogDetail(id);
    }

    @Override
    public List<Blog> getByTypeId(Integer id, Integer current, Integer pagesize) {
        int start = (current - 1) * pagesize;
        List<Blog> list = blogMapper.getByTypeId(id);
        List<Blog> list1 = new ArrayList<>();
        try {
            for (int i = 0; i < start + pagesize; i++) {
                if (i >= start) {
                    if (list.get(i) != null) {
                        list1.add(list.get(i));
                    }
                }
            }
            return list1;
        } catch (Exception e) {
            return list1;
        }
    }


}
