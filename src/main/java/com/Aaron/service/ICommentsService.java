package com.Aaron.service;

import com.Aaron.entity.Comments;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface ICommentsService extends IService<Comments> {

    String postAddComment(Comments comments);

    List<Comments> getComment(int  id);
}
