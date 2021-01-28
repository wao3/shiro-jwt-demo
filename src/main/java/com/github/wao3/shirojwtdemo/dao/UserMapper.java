package com.github.wao3.shirojwtdemo.dao;

import com.github.wao3.shirojwtdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    Integer insertUser(User user);

    User selectByUserName(String username);
}
