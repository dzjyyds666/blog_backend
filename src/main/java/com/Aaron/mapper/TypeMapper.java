package com.Aaron.mapper;

import com.Aaron.entity.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface TypeMapper extends BaseMapper<Type> {

    List<Type> getTypeInfo();

    void reduceTypeNumber(Integer id);

    void getChangeTypeName(Integer id, String typeName);

    void getAddType(String typeName);

    Integer selectIdByName(String typeName);

    void addTypeBlogNumber(Integer id);

    List<Type> getSearch(String search);
}
