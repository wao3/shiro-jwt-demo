package com.github.wao3.shirojwtdemo.controller;

import com.github.wao3.shirojwtdemo.vo.CommonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @PostMapping()
    @RequiresPermissions("blog:insert")
    public CommonResult<String> post() {
        return new CommonResult<String>().success("新增文章成功");
    }

    @GetMapping()
    @RequiresPermissions("blog:select")
    public CommonResult<String> get() {
        return new CommonResult<String>().success("获取文章成功");
    }

    //省略删改
}
