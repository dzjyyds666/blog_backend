package com.Aaron.service;

import com.Aaron.entity.Tokenblacklist;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-25
 */
public interface ITokenblacklistService extends IService<Tokenblacklist> {

    boolean tokenIsEffective(String token);
}
