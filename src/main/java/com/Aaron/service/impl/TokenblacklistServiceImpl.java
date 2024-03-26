package com.Aaron.service.impl;

import com.Aaron.entity.Tokenblacklist;
import com.Aaron.mapper.TokenblacklistMapper;
import com.Aaron.service.ITokenblacklistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-25
 */
@Service
public class TokenblacklistServiceImpl extends ServiceImpl<TokenblacklistMapper, Tokenblacklist> implements ITokenblacklistService {

    @Autowired
    private TokenblacklistMapper tokenblacklistMapper;

    @Override
    public boolean tokenIsEffective(String token) {
        List<String> list = tokenblacklistMapper.list();
        if(list != null){
            for (String blacktoken : list) {
                if (Objects.equals(token, blacktoken)) {
                    //token存在黑名单，则返回false
                    return false;
                }
            }
            //token不存在黑名单，则放行
            return true;
        }
        //黑名单为空
        else return true;
    }
}
