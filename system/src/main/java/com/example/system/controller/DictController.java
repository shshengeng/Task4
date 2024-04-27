package com.example.system.controller;

import com.example.common.response.ResponseResult;
import com.example.system.model.Dict;
import com.example.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * 完成了增删改查,导出Excel,分页的功能
 */


@RestController("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    //查单个
    @GetMapping("/{dictId}")
    public ResponseResult getDict(@PathVariable Long dictId){
        Dict dict = dictService.selectById(dictId);
        if(dict == null){
            return new ResponseResult<>(400, "get dict failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get dict successfully", dict, System.currentTimeMillis());
    }

    //查所有
    @GetMapping("")
    public ResponseResult getAllDict(){
        List<Dict> dicts = dictService.selectAll();
        if(dicts == null){
            return new ResponseResult<>(400, "get dict failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get dict successfully", dicts, System.currentTimeMillis());
    }


    //增
    @PostMapping("")
    public ResponseResult addDept(@RequestBody Dict dict){
        int i = dictService.insert(dict);
        if(i == -1){
            return new ResponseResult<>(400, "add dict failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "add dict successfully", null, System.currentTimeMillis());
    }

    //改
    @PutMapping("")
    public ResponseResult updateDept(@RequestBody Dict dict){
        int i = dictService.update(dict);
        if(i == -1){
            return new ResponseResult<>(400, "update dict failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "update dict successfully", null, System.currentTimeMillis());
    }

    //删
    @DeleteMapping("/{dictId}")
    public ResponseResult deleteDept(@PathVariable long dictId){
        int i = dictService.deleteById(dictId);
        if(i == -1){
            return new ResponseResult<>(400, "delete dict failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "delete dict successfully", null, System.currentTimeMillis());
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() {
        try {
            // 将数据转换为 Excel 表格格式
            byte[] excelBytes = dictService.exportDataToExcel();

            // 设置响应头，告诉客户端返回的是 Excel 文件
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String encodedFileName = URLEncoder.encode("Dict表.xlsx", StandardCharsets.UTF_8);
            headers.setContentDispositionFormData("attachment", encodedFileName);

            // 返回 ResponseEntity 对象，包含响应头和 Excel 文件数据
            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // 返回错误的 ResponseEntity 对象
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){

        int offset = (pageNum - 1) * pageSize;
        List<Dict> dicts = dictService.selectPage(offset, pageSize);
        if(dicts == null){
            return new ResponseResult<>(400, "get dept failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "find dept successfully", dicts, System.currentTimeMillis());
    }
}
