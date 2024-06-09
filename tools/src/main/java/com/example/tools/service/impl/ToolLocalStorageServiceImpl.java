package com.example.tools.service.impl;

import com.example.tools.mapper.ToolLocalStorageMapper;
import com.example.tools.model.ToolLocalStorage;
import com.example.tools.service.ToolLocalStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class ToolLocalStorageServiceImpl implements ToolLocalStorageService {

    private static final String STORAGE_DIR = "file_storage"; // 本地存储目录

    @Autowired
    private ToolLocalStorageMapper toolLocalStorageMapper;

    @Override
    public ToolLocalStorage uploadFile(MultipartFile file, String createBy) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path filePath = Paths.get(STORAGE_DIR, fileName);

        // 创建存储目录
        Files.createDirectories(filePath.getParent());
        file.transferTo(filePath.toFile());

        ToolLocalStorage metadata = new ToolLocalStorage();
        metadata.setRealName(fileName);
        metadata.setName(fileName);
        metadata.setSuffix(StringUtils.getFilenameExtension(fileName));
        metadata.setPath(filePath.toString());
        metadata.setType(Files.probeContentType(filePath));
        metadata.setSize(String.valueOf(file.getSize()));
        metadata.setCreateBy(createBy);
        metadata.setCreateTime(new Date());
        metadata.setUpdateBy(createBy);
        metadata.setUpdateTime(new Date());

        toolLocalStorageMapper.insert(metadata);

        return metadata;
    }

    @Override
    public List<ToolLocalStorage> listFiles(int page, int size) {
        int offset = (page - 1) * size;
        return toolLocalStorageMapper.selectPaged(offset, size);
    }

    @Override
    public List<ToolLocalStorage> exportData() {
        return toolLocalStorageMapper.selectAll();
    }

    @Override
    public byte[] getFileContent(Long storageId) throws IOException {
        ToolLocalStorage metadata = toolLocalStorageMapper.selectById(storageId);
        if (metadata != null) {
            Path filePath = Paths.get(metadata.getPath());
            return Files.readAllBytes(filePath);
        }
        return null;
    }

    @Override
    public ToolLocalStorage getFileMetadata(Long storageId) {
        return toolLocalStorageMapper.selectById(storageId);
    }
}
