package com.Aaron.controller;

import com.Aaron.entity.Settings;
import com.Aaron.service.ISettingsService;
import com.Aaron.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-03-23
 */
@RestController
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private ISettingsService settingsService;

    @PostMapping("/edit")
    public Result postEdit(@RequestBody Settings settings){

        System.out.println(settings);

        settingsService.postEdit(settings);

        return Result.Success();
    }


    @GetMapping("/front/getContent")
    public Result getContent(String name){

        String content = settingsService.getContent(name);
        return Result.Success("获取成功",content);
    }

}
