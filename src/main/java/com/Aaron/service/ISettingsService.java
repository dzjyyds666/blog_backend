package com.Aaron.service;

import com.Aaron.entity.Settings;
import com.baomidou.mybatisplus.extension.service.IService;



/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface ISettingsService extends IService<Settings> {

    void postEdit(Settings settings);



    String getContent(String name);
}
