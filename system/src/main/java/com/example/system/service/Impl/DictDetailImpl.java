package com.example.system.service.Impl;

import com.example.system.mapper.DictDetailMapper;
import com.example.system.model.DictDetail;
import com.example.system.service.DictDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictDetailImpl implements DictDetailService {

    @Autowired
    private DictDetailMapper dictDetailMapper;

    @Override
    public DictDetail selectById(Long id) {
        return dictDetailMapper.selectById(id);
    }

    @Override
    public List<DictDetail> selectAll() {
        return dictDetailMapper.selectAll();
    }

    @Override
    public int insert(DictDetail dictDetail) {
        return dictDetailMapper.insert(dictDetail);
    }

    @Override
    public int update(DictDetail dictDetail) {
        return dictDetailMapper.update(dictDetail);
    }

    @Override
    public int deleteById(Long id) {
        return dictDetailMapper.deleteById(id);
    }

    @Override
    public List selectPage(Integer pageNum, Integer pageSize) {
        return dictDetailMapper.selectPage(pageNum,pageSize);
    }
}
