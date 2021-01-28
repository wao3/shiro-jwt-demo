package com.github.wao3.shirojwtdemo.controller;

import com.github.wao3.shirojwtdemo.entity.User;
import com.github.wao3.shirojwtdemo.service.UserService;
import com.github.wao3.shirojwtdemo.utils.JWTUtil;
import com.github.wao3.shirojwtdemo.vo.CommonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/register")
    public CommonResult<String>  register(User user) {
        boolean success = userService.InsertUser(user);
        if(success) return new CommonResult<String>().success("注册成功");
        else return new CommonResult<String>().setCode(400).setMessage("注册失败");
    }

    @PostMapping("/login")
    public CommonResult<String> login(User user) {
        if(userService.verifyUser(user)) {
            String token = JWTUtil.createToken(user.getUsername());
            return new CommonResult<String>().success(token);
        } else {
            return new CommonResult<String>().setCode(400).setMessage("用户名或密码错误");
        }
    }

    @RequestMapping("/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @DeleteMapping("/{userid}")
    @RequiresPermissions("user:delete")
    public CommonResult<String> delete(@PathVariable("userid") int user) {
        return new CommonResult<String>().success("删除用户成功");
    }

    @PatchMapping("/{userid}")
    @RequiresPermissions("user:update")
    public CommonResult<String> update(@PathVariable("userid") int user) {
        return new CommonResult<String>().success("更新用户成功");
    }
}
