package com.example.system.security;

import com.example.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/online")
public class OnlineController {

    @Autowired
    private RedisUtil redisUtil;

    private static final String ONLINE_USERS_KEY = "onlineUsers";

    // 查询在线用户
    @GetMapping("/users")
    public List<Object> getOnlineUsers() {
        return redisUtil.getOnlineUsers(ONLINE_USERS_KEY);
    }

    // 导出在线用户
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportOnlineUsers() throws IOException {
        List<Object> onlineUsers = getOnlineUsers();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Online Users");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("User");

            int rowNum = 1;
            for (Object user : onlineUsers) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.toString());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] bytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "online_users.xlsx");

            return ResponseEntity.ok().headers(headers).body(bytes);
        }
    }

    // 强制退出用户
    @PostMapping("/logout")
    public ResponseEntity<String> forceLogout(@RequestParam String username) {
        boolean removed = redisUtil.removeOnlineUser(ONLINE_USERS_KEY, username);
        if (removed) {
            return ResponseEntity.ok("User " + username + " has been logged out.");
        }
        return ResponseEntity.status(404).body("User not found.");
    }
}
