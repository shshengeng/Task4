package com.example.system.controller;

import com.example.common.response.ResponseResult;
import com.example.system.model.Role;
import com.example.system.service.RoleService;
import com.example.system.service.UserRoleService;
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
 * 实现角色增删查改、分页、导出、权限分配（给角色分配权限）
 */

@RestController("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/{roleId}")
    public ResponseResult getRole(@PathVariable Long roleId){
        Role role = roleService.selectById(roleId);
        if(role == null){
            return new ResponseResult<>(400, "get role failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get role successfully", role, System.currentTimeMillis());
    }

    //查所有
    @GetMapping("")
    public ResponseResult getAllRole(){
        List<Role> roles = roleService.selectAll();
        if(roles == null){
            return new ResponseResult<>(400, "get role failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "get role successfully", roles, System.currentTimeMillis());
    }


    //增
    @PostMapping("")
    public ResponseResult addRole(@RequestBody Role role){
        int i = roleService.insert(role);
        if(i == -1){
            return new ResponseResult<>(400, "add role failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "add role successfully", null, System.currentTimeMillis());
    }

    //改
    @PutMapping("")
    public ResponseResult updateRole(@RequestBody Role role){
        int i = roleService.update(role);
        if(i == -1){
            return new ResponseResult<>(400, "update role failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "update role successfully", null, System.currentTimeMillis());
    }

    //删
    @DeleteMapping("/{roleId}")
    public ResponseResult deleteUser(@PathVariable long roleId){
        int i = roleService.deleteById(roleId);
        if(i == -1){
            return new ResponseResult<>(400, "delete role failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "delete role successfully", null, System.currentTimeMillis());
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() {
        try {
            // 将数据转换为 Excel 表格格式
            byte[] excelBytes = roleService.exportDataToExcel();

            // 设置响应头，告诉客户端返回的是 Excel 文件
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String encodedFileName = URLEncoder.encode("Role表.xlsx", StandardCharsets.UTF_8);
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
        List<Role> roles = roleService.selectPage(offset, pageSize);
        if(roles == null){
            return new ResponseResult<>(400, "get role failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "find role successfully", roles, System.currentTimeMillis());
    }


    @PostMapping("/permission")
    public ResponseResult addPermission(@RequestParam Long userId,@RequestParam Long roleId){
        int i = userRoleService.savePermission(userId, roleId);
        if(i == -1){
            return new ResponseResult<>(400, "add role failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "add role successfully", null, System.currentTimeMillis());
    }

}
