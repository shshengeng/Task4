package com.example.logging.controller;

import com.example.logging.service.LogService;
import com.example.system.model.Log;
import jakarta.annotation.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.common.response.ResponseResult;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LogController {

    @Resource
    private LogService logService;

    @GetMapping("/api/log")
    public ResponseResult getAllLog() {
        List<Log> all = logService.getAll();
        if (all.isEmpty()) {
            return new ResponseResult<>(500, "get log failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get log successful", all, System.currentTimeMillis());

    }

    @GetMapping("/api/log/{id}")
    public ResponseResult getLogById(@PathVariable Long id) {
        Log byId = logService.getById(id);
        if (byId == null) {
            return new ResponseResult<>(500, "get log failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get log successful", byId, System.currentTimeMillis());
    }


    @GetMapping("/api/log/exception")
    public ResponseResult getAllExceptionLog() {
        List<Log> all = logService.getAll();
        List<String> list = new ArrayList<>();
        for (Log log : all) {
            list.add(log.getExceptionDetail());
        }
        if (all.isEmpty()) {
            return new ResponseResult<>(500, "get exception log failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get exception log successful", list, System.currentTimeMillis());

    }

    @GetMapping("/api/log/exception/{id}")
    public ResponseResult getExceptionLogById(@PathVariable Long id) {
        Log byId = logService.getById(id);
        if (byId == null) {
            return new ResponseResult<>(500, "get exception log failed", null, System.currentTimeMillis());
        }
        String exceptionDetail = byId.getExceptionDetail();
        return new ResponseResult<>(200, "get exception log successful", exceptionDetail, System.currentTimeMillis());
    }


    @GetMapping("/api/log/export")
    public ResponseEntity<byte[]> exportLogToExcel() {
        try {
            // 将数据转换为 Excel 表格格式
            byte[] excelBytes = logService.exportDataToExcel();

            // 设置响应头，告诉客户端返回的是 Excel 文件
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String encodedFileName = URLEncoder.encode("Log表.xlsx", StandardCharsets.UTF_8);
            headers.setContentDispositionFormData("attachment", encodedFileName);

            // 返回 ResponseEntity 对象，包含响应头和 Excel 文件数据
            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // 返回错误的 ResponseEntity 对象
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
