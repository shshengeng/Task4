package com.example.system.service;

import com.example.system.model.User;

import java.util.List;

public interface UserService {

    User selectById(Long id);

    List<User> selectAll();

    int insert(User user);

    int update(User user);

    int deleteById(Long id);

    byte[] exportDataToExcel() throws Exception;

    List selectPage(Integer pageNum, Integer pageSize);
}
