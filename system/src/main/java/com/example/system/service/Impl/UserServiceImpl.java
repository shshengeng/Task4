package com.example.system.service.Impl;

import com.example.system.mapper.UserMapper;
import com.example.system.model.User;
import com.example.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        User user = userMapper.findByUsername(name);
        return user;
    }
}
