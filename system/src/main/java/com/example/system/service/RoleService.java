package com.example.system.service;

import com.example.system.model.Role;

import java.util.List;

public interface RoleService {


    Role selectById(Long id);

    List<Role> selectAll();

    int insert(Role role);

    int update(Role role);

    int deleteById(Long id);

    byte[] exportDataToExcel() throws Exception;

    List selectPage(Integer pageNum, Integer pageSize);

}
