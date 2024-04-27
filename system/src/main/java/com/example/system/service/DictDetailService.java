package com.example.system.service;

import com.example.system.model.DictDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DictDetailService {

    DictDetail selectById(Long id);

    List<DictDetail> selectAll();

    int insert(DictDetail dictDetail);

    int update(DictDetail dictDetail);

    int deleteById(Long id);

    List selectPage(Integer pageNum, Integer pageSize);
}
