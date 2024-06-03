package com.example.logging.service.impl;

import com.example.logging.mapper.LogMapper;
import com.example.logging.service.LogService;
import com.example.system.model.Dept;
import com.example.system.model.Dict;
import com.example.system.model.Log;
import jakarta.annotation.Resource;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;


    @Override
    public List<Log> getAll() {
        return logMapper.getAll();
    }

    @Override
    public Log getById(Long logId) {
        return logMapper.getById(logId);
    }

    @Override
    public byte[] exportDataToExcel() throws Exception {
        List<Log> list = logMapper.getAll();

        // 创建 Excel 工作簿和工作表
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Log表");

        // 填充 Excel 数据
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        Field[] declaredFields = Log.class.getDeclaredFields();
        row.createCell(0).setCellValue("序号");
        for (int i = 0; i < declaredFields.length; i++){
            row.createCell(i+1).setCellValue(declaredFields[i].getName());
        }

        for (Log log : list){
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowNum-1);
            Field[] fields = log.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++){
                fields[i].setAccessible(true);
                Object o = fields[i].get(log);
                if(o != null){
                    row.createCell(i+1).setCellValue(String.valueOf(o));
                }
            }
        }
        // 将 Excel 文件写入到 ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        outputStream.close();

        return outputStream.toByteArray();
    }
}
