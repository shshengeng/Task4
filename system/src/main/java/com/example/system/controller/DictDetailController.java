package com.example.system.controller;

import com.example.common.response.ResponseResult;
import com.example.system.model.DictDetail;
import com.example.system.service.DictDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("/dictDetail")
public class DictDetailController {

    @Autowired
    private DictDetailService dictDetailService;

    //查单个
    @GetMapping("/{detailId}")
    public ResponseResult getDictDetail(@PathVariable Long detailId){
        DictDetail dictDetail = dictDetailService.selectById(detailId);
        if(dictDetail == null){
            return new ResponseResult<>(400, "get dictDetail failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get dictDetail successfully", dictDetail, System.currentTimeMillis());
    }

    //查所有
    @GetMapping("")
    public ResponseResult getAllDictDetail(){
        List<DictDetail> dictDetails = dictDetailService.selectAll();
        if(dictDetails == null){
            return new ResponseResult<>(400, "get dictDetail failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get dictDetail successfully", dictDetails, System.currentTimeMillis());
    }


    //增
    @PostMapping("")
    public ResponseResult addDictDetail(@RequestBody DictDetail dictDetail){
        int i = dictDetailService.insert(dictDetail);
        if(i == -1){
            return new ResponseResult<>(400, "add dictDetail failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "add didictDetailct successfully", null, System.currentTimeMillis());
    }

    //改
    @PutMapping("")
    public ResponseResult updateDictDetail(@RequestBody DictDetail dictDetail){
        int i = dictDetailService.update(dictDetail);
        if(i == -1){
            return new ResponseResult<>(400, "update dictDetail failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "update dictDetail successfully", null, System.currentTimeMillis());
    }

    //删
    @DeleteMapping("/{detailId}")
    public ResponseResult deleteDictDetail(@PathVariable long detailId){
        int i = dictDetailService.deleteById(detailId);
        if(i == -1){
            return new ResponseResult<>(400, "delete dictDetail failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "delete dictDetail successfully", null, System.currentTimeMillis());
    }


    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){

        int offset = (pageNum - 1) * pageSize;
        List<DictDetail> dictDetails = dictDetailService.selectPage(offset, pageSize);
        if(dictDetails == null){
            return new ResponseResult<>(400, "get dept failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "find dept successfully", dictDetails, System.currentTimeMillis());
    }


}
