package com.Aaron.mapper;

import com.Aaron.entity.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface CommentsMapper extends BaseMapper<Comments> {

    void postAddComment(Comments comments);

    List<Comments> getComment(int id);
}
