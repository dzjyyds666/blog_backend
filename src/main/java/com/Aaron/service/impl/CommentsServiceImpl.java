package com.Aaron.service.impl;

import com.Aaron.entity.Comments;
import com.Aaron.mapper.BlogMapper;
import com.Aaron.mapper.CommentsMapper;
import com.Aaron.service.ICommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public String postAddComment(Comments comments) {
        try {
            commentsMapper.postAddComment(comments);
            blogMapper.updateCommentNum(comments.getBlogId());
            return "评论成功";
        } catch (Exception e) {
            return "评论失败,昵称重复";
        }
    }

    @Override
    public List<Comments> getComment(int id) {
        return commentsMapper.getComment(id);
    }
}
