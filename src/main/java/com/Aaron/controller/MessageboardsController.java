package com.Aaron.controller;

import com.Aaron.entity.Messageboards;
import com.Aaron.service.IMessageboardsService;
import com.Aaron.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 留言板 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@RestController
@RequestMapping("/messageboards")
public class MessageboardsController {

    @Autowired
    private IMessageboardsService messageboardsService;


    @GetMapping("/front/getComment")
    public Result getComment(){
        List<Messageboards> list = messageboardsService.getComment();
        return Result.Success(list);
    }

    @GetMapping("/front/getNewtime")
    public Result getNewTime(){
        LocalDateTime newTime = messageboardsService.getNewTime();
        return Result.Success(newTime);
    }

    @PostMapping("/front/addComment")
    public Result postAddComment(@RequestBody Messageboards messageboards){
        String message = messageboardsService.postAddComment(messageboards);

        if(message.contains("失败")){
            return Result.fail(201,message);
        }
        return Result.Success(200,message,null);
    }

}
