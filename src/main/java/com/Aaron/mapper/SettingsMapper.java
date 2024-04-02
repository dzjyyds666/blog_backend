package com.Aaron.mapper;

import com.Aaron.entity.Settings;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface SettingsMapper extends BaseMapper<Settings> {

    void postEdit(Settings settings);

    String getContent(String name);
}
