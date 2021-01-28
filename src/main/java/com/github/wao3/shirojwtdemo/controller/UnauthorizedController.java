package com.github.wao3.shirojwtdemo.controller;

import com.github.wao3.shirojwtdemo.vo.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnauthorizedController {

    @RequestMapping("/unauthorized")
    public CommonResult<String> delete(String message) {
        message = message == null ? "没有权限" : message;

        return new CommonResult<String>().setCode(401).setMessage(message);
    }
}
