package com.example.system.service.Impl;

import com.example.system.mapper.UserRoleMapper;
import com.example.system.model.UsersRoles;
import com.example.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public List<UsersRoles> getPermission(Long userId) {
        return userRoleMapper.getPermission(userId);
    }

    @Override
    public int savePermission(Long userId, Long roleId) {
        return userRoleMapper.savePermission(userId,roleId);
    }
}
