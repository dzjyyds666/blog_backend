package com.Aaron.service.impl;

import com.Aaron.entity.Messageboards;
import com.Aaron.mapper.MessageboardsMapper;
import com.Aaron.service.IMessageboardsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 留言板 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@Service
public class MessageboardsServiceImpl extends ServiceImpl<MessageboardsMapper, Messageboards> implements IMessageboardsService {

    @Autowired
    private MessageboardsMapper messageboardsMapper;

    @Override
    public List<Messageboards> getComment() {
        return messageboardsMapper.getComment();
    }

    @Override
    public LocalDateTime getNewTime() {
        return messageboardsMapper.getNewTime();
    }

    @Override
    public String postAddComment(Messageboards messageboards) {
        try{
            messageboardsMapper.postAddComment(messageboards);
            return "感谢留言";
        }catch(Exception e){
            return "留言失败,请检查昵称或邮箱是否重复";
        }
    }
}
