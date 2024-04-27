package com.example.system.service;

import com.example.system.model.Dict;

import java.util.List;

public interface DictService {

    Dict selectById(Long id);

    List<Dict> selectAll();

    int insert(Dict dict);

    int update(Dict dict);

    int deleteById(Long id);

    byte[] exportDataToExcel() throws Exception;

    List<Dict> selectPage(Integer pageNum, Integer pageSize);
}
