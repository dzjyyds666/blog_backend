package com.Aaron.service.impl;

import com.Aaron.entity.Settings;
import com.Aaron.mapper.SettingsMapper;
import com.Aaron.service.ISettingsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@Service
public class SettingsServiceImpl extends ServiceImpl<SettingsMapper, Settings> implements ISettingsService {

    @Autowired
    private SettingsMapper settingsMapper;


    @Override
    public void postEdit(Settings settings) {
        settingsMapper.postEdit(settings);
    }

    @Override
    public String getContent(String name) {

        return settingsMapper.getContent(name);
    }


}
