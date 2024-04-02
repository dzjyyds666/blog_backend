package com.Aaron.mapper;

import com.Aaron.entity.Messageboards;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 留言板 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface MessageboardsMapper extends BaseMapper<Messageboards> {

    List<Messageboards> getComment();

    LocalDateTime getNewTime();

    void postAddComment(Messageboards messageboards);
}
