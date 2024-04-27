package com.example.system.service.Impl;

import com.example.system.mapper.DeptMapper;
import com.example.system.model.Dept;
import com.example.system.service.DeptService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;


    @Override
    public Dept selectById(Long id) {
        return deptMapper.selectById(id);
    }

    @Override
    public List<Dept> selectAll() {
        return deptMapper.selectAll();
    }

    @Override
    public int insert(Dept dept) {
        return deptMapper.insert(dept);
    }

    @Override
    public int update(Dept dept) {
        return deptMapper.update(dept);
    }

    @Override
    public int deleteById(Long id) {
        return deptMapper.deleteById(id);
    }

    @Override
    public byte[] exportDataToExcel() throws Exception{
        List<Dept> list = deptMapper.selectAll();

        // 创建 Excel 工作簿和工作表
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Dept表");

        // 填充 Excel 数据
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        Field[] declaredFields = Dept.class.getDeclaredFields();
        row.createCell(0).setCellValue("序号");
        for (int i = 0; i < declaredFields.length; i++){
            row.createCell(i+1).setCellValue(declaredFields[i].getName());
        }

        for (Dept dept : list){
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowNum-1);
            Field[] fields = dept.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++){
                fields[i].setAccessible(true);
                Object o = fields[i].get(dept);
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

    @Override
    public List<Dept> selectPage(Integer pageNum, Integer pageSize) {
        return deptMapper.selectPage(pageNum, pageSize);
    }
}
