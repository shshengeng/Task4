package com.example.system.service;

import com.example.system.model.UsersRoles;
import java.util.List;

public interface UserRoleService {


    List<UsersRoles> getPermission(Long userId);

    int savePermission(Long userId,Long roleId);
}
