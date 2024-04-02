package com.Aaron.service.impl;

import com.Aaron.entity.Type;
import com.Aaron.mapper.BlogMapper;
import com.Aaron.mapper.BtcontactMapper;
import com.Aaron.mapper.TypeMapper;
import com.Aaron.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private BtcontactMapper btcontactMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Type> getTypeInfo() {
        return typeMapper.getTypeInfo();
    }

    @Override
    public List<Type> getSearchType(String typeName) {

        List<Type> list = typeMapper.getTypeInfo();
        List<Type> list1 = new ArrayList<>();
        for (Type type : list) {
            if (typeName != null) {
                if (type.getTypeName().contains(typeName)) {
                    System.out.println("分类名称匹配完成");
                } else continue;
            }
            list1.add(type);
        }

        return list1;
    }

    @Override
    public List<Type> getChangeTypeName(Integer id, String typeName) {
        typeMapper.getChangeTypeName(id,typeName);

        return typeMapper.getTypeInfo();
    }

    @Override
    public String getAddType(String typeName) {
        try {
            typeMapper.getAddType(typeName);
            return "添加成功";
        }catch (Exception e){
            return "已存在该分类，请重新添加";
        }
    }

    @Override
    public String postDeleteType(List<Type> typeList) {
        try{
            //删除分类要把分类下的博客全部删除，如果一个博客属于多个分类，则不删除该博客
            for(Type type:typeList){
                //先查询该分类下的博客id
                List<Integer> blogIdList = btcontactMapper.selectBlogIdByTypeId(type.getTypeId());
                //在查看每个博客对应的分类数目
                for(Integer id:blogIdList){
                    List<Integer> typeIdList = btcontactMapper.selectTypeIdByBlogId(id);
                    if(typeIdList.size() == 1){
                        //该博客只对应一个分类，则删除该博客
                        //先删除btconnect中博客分类对应信息
                        btcontactMapper.deleteByBlogId(id);
                        //然后删除博客
                        blogMapper.deleteById(id);
                    }
                }
                //删除btconnect中的typeId信息
                btcontactMapper.deleteByTypeId(type.getTypeId());

                //最后删除该分类
                typeMapper.deleteById(type.getTypeId());
            }
            return "删除成功";
        }catch (Exception e){
            return "删除失败";
        }
    }

    @Override
    public List<Type> getSearch(String search) {
        return typeMapper.getSearch(search);
    }

    @Override
    public Type getTYpeById(Integer id) {
        return typeMapper.selectById(id);
    }
}
