package com.github.wao3.shirojwtdemo.controller;

import com.github.wao3.shirojwtdemo.vo.CommonResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ExceptionHandler({ShiroException.class, AuthorizationException.class})
    public CommonResult<Object> handle401() {
        return new CommonResult<>().setCode(401).setMessage("没有权限");
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public CommonResult<Object> globalException(HttpServletRequest request, Throwable ex) {
        return new CommonResult<>()
                .setCode(getStatus(request).value())
                .setMessage("访问出错，无法访问: " + ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
