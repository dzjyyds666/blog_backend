package com.Aaron.mapper;

import com.Aaron.entity.Tokenblacklist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-03-25
 */
public interface TokenblacklistMapper extends BaseMapper<Tokenblacklist> {

    List<String> list();

    void insertToken(String token);
}
