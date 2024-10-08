package com.Aaron.service;

import com.Aaron.entity.Messageboards;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 留言板 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface IMessageboardsService extends IService<Messageboards> {

    List<Messageboards> getComment();

    LocalDateTime getNewTime();

    String postAddComment(Messageboards messageboards);
}
