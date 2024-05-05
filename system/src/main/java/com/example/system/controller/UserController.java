package com.example.system.controller;

import com.example.common.response.ResponseResult;
import com.example.system.model.User;
import com.example.system.service.UserService;
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
 * 1、实现用户增删查改、分页、数据导出
 */
@RestController("/user")
public class UserController {

    @Autowired
    private  UserService userService;

    @GetMapping("/{userId}")
    public ResponseResult getUser(@PathVariable Long userId){
        User user = userService.selectById(userId);
        if(user == null){
            return new ResponseResult<>(400, "get user failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get user successfully", user, System.currentTimeMillis());
    }

    //查所有
    @GetMapping("")
    public ResponseResult getAllUser(){
        List<User> users = userService.selectAll();
        if(users == null){
            return new ResponseResult<>(400, "get user failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get user successfully", users, System.currentTimeMillis());
    }


    //增
    @PostMapping("")
    public ResponseResult addUser(@RequestBody User user){
        int i = userService.insert(user);
        if(i == -1){
            return new ResponseResult<>(400, "add user failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "add user successfully", null, System.currentTimeMillis());
    }

    //改
    @PutMapping("")
    public ResponseResult updateUser(@RequestBody User user){
        int i = userService.update(user);
        if(i == -1){
            return new ResponseResult<>(400, "update user failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "update user successfully", null, System.currentTimeMillis());
    }

    //删
    @DeleteMapping("/{userId}")
    public ResponseResult deleteUser(@PathVariable long userId){
        int i = userService.deleteById(userId);
        if(i == -1){
            return new ResponseResult<>(400, "delete user failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "delete user successfully", null, System.currentTimeMillis());
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() {
        try {
            // 将数据转换为 Excel 表格格式
            byte[] excelBytes = userService.exportDataToExcel();

            // 设置响应头，告诉客户端返回的是 Excel 文件
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String encodedFileName = URLEncoder.encode("User表.xlsx", StandardCharsets.UTF_8);
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
        List<User> users = userService.selectPage(offset, pageSize);
        if(users == null){
            return new ResponseResult<>(400, "get user failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "find user successfully", users, System.currentTimeMillis());
    }
}