package com.Aaron.service.impl;

import com.Aaron.entity.Messageboards;
import com.Aaron.mapper.MessageboardsMapper;
import com.Aaron.service.IMessageboardsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
