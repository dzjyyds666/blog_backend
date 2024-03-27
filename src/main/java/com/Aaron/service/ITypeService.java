package com.Aaron.service;

import com.Aaron.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface ITypeService extends IService<Type> {

    List<Type> getTypeInfo();

    List<Type> getSearchType(String typeName);

    List<Type> getChangeTypeName(Integer id, String typeName);

    String getAddType(String typeName);

    String postDeleteType(List<Type> typeList);
}
