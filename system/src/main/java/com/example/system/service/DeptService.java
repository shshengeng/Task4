package com.example.system.service;

import com.example.system.model.Dept;


import java.util.List;

public interface DeptService {

    Dept selectById(Long id);

    List<Dept> selectAll();

    int insert(Dept dept);

    int update(Dept dept);

    int deleteById(Long id);

    byte[] exportDataToExcel() throws Exception;

    List<Dept> selectPage(Integer pageNum, Integer pageSize);

}
