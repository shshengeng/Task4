package com.example.tools.controller;

import com.example.tools.model.ToolLocalStorage;
import com.example.tools.service.ToolLocalStorageService;
import com.example.tools.service.ToolLocalStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/local-storage")
public class LocalStorageController {

    @Autowired
    private ToolLocalStorageService toolLocalStorageService;

    @PostMapping("/upload")
    public ResponseEntity<ToolLocalStorage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("createBy") String createBy) {
        try {
            ToolLocalStorage metadata = toolLocalStorageService.uploadFile(file, createBy);
            return new ResponseEntity<>(metadata, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ToolLocalStorage>> listFiles(@RequestParam("page") int page, @RequestParam("size") int size) {
        List<ToolLocalStorage> files = toolLocalStorageService.listFiles(page, size);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @GetMapping("/export")
    public ResponseEntity<List<ToolLocalStorage>> exportData() {
        List<ToolLocalStorage> data = toolLocalStorageService.exportData();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Long id) {
        try {
            ToolLocalStorage metadata = toolLocalStorageService.getFileMetadata(id);
            byte[] fileContent = toolLocalStorageService.getFileContent(id);
            if (fileContent != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (metadata != null ? metadata.getRealName() : "file"));
                return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
