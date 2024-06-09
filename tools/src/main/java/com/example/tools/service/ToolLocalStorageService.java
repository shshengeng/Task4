package com.example.tools.service;

import com.example.tools.model.ToolLocalStorage;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ToolLocalStorageService {
    ToolLocalStorage uploadFile(MultipartFile file, String createBy) throws IOException;
    List<ToolLocalStorage> listFiles(int page, int size);
    List<ToolLocalStorage> exportData();
    byte[] getFileContent(Long storageId) throws IOException;
    ToolLocalStorage getFileMetadata(Long storageId);
}

