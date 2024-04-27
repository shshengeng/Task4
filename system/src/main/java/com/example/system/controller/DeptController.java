package com.example.system.controller;


import com.example.common.response.ResponseResult;
import com.example.system.model.Dept;
import com.example.system.service.DeptService;
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

@RestController("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查单个
    @GetMapping("/{deptId}")
    public ResponseResult getDept(@PathVariable Long deptId){
        Dept dept = deptService.selectById(deptId);
        if(dept == null){
            return new ResponseResult<>(400, "get dept failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get dept successfully", dept, System.currentTimeMillis());
    }

    //查所有
    @GetMapping("")
    public ResponseResult getAllDept(){
        List<Dept> depts = deptService.selectAll();
        if(depts == null){
            return new ResponseResult<>(400, "get dept failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get dept successfully", depts, System.currentTimeMillis());
    }


    //增
    @PostMapping("")
    public ResponseResult addDept(@RequestBody Dept dept){
        int i = deptService.insert(dept);
        if(i == -1){
            return new ResponseResult<>(400, "add dept failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "add dept successfully", null, System.currentTimeMillis());
    }

    //改
    @PutMapping("")
    public ResponseResult updateDept(@RequestBody Dept dept){
        int i = deptService.update(dept);
        if(i == -1){
            return new ResponseResult<>(400, "update dept failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "update dept successfully", null, System.currentTimeMillis());
    }

    //删
    @DeleteMapping("/{deptId}")
    public ResponseResult deleteDept(@PathVariable long deptId){
        int i = deptService.deleteById(deptId);
        if(i == -1){
            return new ResponseResult<>(400, "delete dept failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "delete dept successfully", null, System.currentTimeMillis());
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() {
        try {
            // 将数据转换为 Excel 表格格式
            byte[] excelBytes = deptService.exportDataToExcel();

            // 设置响应头，告诉客户端返回的是 Excel 文件
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String encodedFileName = URLEncoder.encode("Dept表.xlsx", StandardCharsets.UTF_8);
            headers.setContentDispositionFormData("attachment", encodedFileName);


            // 返回 ResponseEntity 对象，包含响应头和 Excel 文件数据
            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // 返回错误的 ResponseEntity 对象
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     1.性能问题：当数据量很大时，数据库执行分页查询可能会变得缓慢，特别是在 offset 较大的情况下。
     这是因为数据库需要跳过大量的记录才能找到分页的起始位置，可能需要执行全表扫描，导致性能下降。
     2.内存消耗：通常情况下，分页查询会将查询结果全部加载到内存中，如果返回的数据量很大，可能会消耗大量的内存资源，甚至导致内存溢出的问题。
     3.数据一致性：在进行分页查询时，数据库中的数据可能会发生变化，例如有新数据插入或已有数据删除。
     如果在分页查询期间发生了数据变化，可能会导致分页结果不一致或遗漏某些数据。
     4.并发问题：大量用户同时进行分页查询操作时，可能会对数据库服务器造成较高的并发压力，影响系统的稳定性和性能。
     * @param pageNum
     * @param pageSize
     * @return
     */

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){

        int offset = (pageNum - 1) * pageSize;
        List<Dept> depts = deptService.selectPage(offset, pageSize);
        if(depts == null){
            return new ResponseResult<>(400, "get dept failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "find dept successfully", depts, System.currentTimeMillis());
    }
}
