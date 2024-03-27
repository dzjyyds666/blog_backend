package com.Aaron.mapper;

import com.Aaron.entity.Btcontact;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 博客分类对应表 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
public interface BtcontactMapper extends BaseMapper<Btcontact> {

    void deleteByBlogId(Integer id);

    List<Integer> selectBlogIdByTypeId(Integer id);

    List<Integer> selectTypeIdByBlogId(Integer id);


    void deleteByTypeId(Integer id);
}
