package com.Aaron.controller;

import com.Aaron.entity.Type;
import com.Aaron.service.ITypeService;
import com.Aaron.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @GetMapping("/getType")
    public Result getTypeInfo() {
        List<Type> list = typeService.getTypeInfo();
        return Result.Success(list);
    }

    @GetMapping("/searchType")
    public Result getSearchType(String typeName) {
        List<Type> list = typeService.getSearchType(typeName);
        return Result.Success(list);
    }

    @GetMapping("/changeName")
    public Result getChangeTypeName(Integer id, String typeName) {
        System.out.println(id);
        System.out.println(typeName);
        List<Type> list = typeService.getChangeTypeName(id, typeName);
        return Result.Success(list);
    }


    @GetMapping("/addType")
    public Result getAddType(String typeName) {

        String message = typeService.getAddType(typeName);
        if(message.contains("已存在该分类")){
            return Result.fail(201,message);
        }
        return Result.Success(200,message,null);
    }

    @PostMapping("/deleteType")
    public Result postDeleteType(@RequestBody List<Type> typeList){
        String message = typeService.postDeleteType(typeList);
        if(message.contains("失败")){
            return Result.fail(201,message);
        }
        return Result.Success(200,message,null);
    }
}
