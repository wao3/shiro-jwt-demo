package com.github.wao3.shirojwtdemo.service;

import com.github.wao3.shirojwtdemo.dao.UserMapper;
import com.github.wao3.shirojwtdemo.entity.User;
import com.github.wao3.shirojwtdemo.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

// 省略接口，直接写实现
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public boolean InsertUser(User user) {
        String salt = SaltUtil.getSalt(8);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex()).setSalt(salt);

        return userMapper.insertUser(user) == 1;
    }

    public User selectUserByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    public boolean verifyUser(User user) {
        if(user == null || user.getUsername() == null || user.getPassword() == null) {
            return false;
        }
        User realUser = selectUserByUserName(user.getUsername());
        if(realUser == null) {
            return false;
        }
        String password = new Md5Hash(user.getPassword(), realUser.getSalt(), 1024).toHex();

        return password.equals(realUser.getPassword());
    }
}
