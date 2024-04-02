package com.Aaron.controller;

import com.Aaron.entity.Comments;
import com.Aaron.service.ICommentsService;
import com.Aaron.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private ICommentsService commentsService;

    @PostMapping("/front/addComment")
    public Result postAddComment(@RequestBody Comments comments){
        String message = commentsService.postAddComment(comments);
        if(message.contains("失败")){
            return Result.fail(201,message);
        }
        return Result.Success(200,message,null);
    }

    @GetMapping("/front/getComment")
    public Result getCommentById(int id){
        List<Comments> comments = commentsService.getComment(id);
        return Result.Success(comments);
    }

}
