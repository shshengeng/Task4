package com.example.system.service.Impl;

import com.example.system.mapper.RoleMapper;
import com.example.system.model.Role;
import com.example.system.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public Role selectById(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public int insert(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public int deleteById(Long id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public byte[] exportDataToExcel() throws Exception {
        List<Role> list = roleMapper.selectAll();

        // 创建 Excel 工作簿和工作表
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Role表");

        // 填充 Excel 数据
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        Field[] declaredFields = Role.class.getDeclaredFields();
        row.createCell(0).setCellValue("序号");
        for (int i = 0; i < declaredFields.length; i++){
            row.createCell(i+1).setCellValue(declaredFields[i].getName());
        }

        for (Role role : list){
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowNum-1);
            Field[] fields = role.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++){
                fields[i].setAccessible(true);
                Object o = fields[i].get(role);
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
    public List selectPage(Integer pageNum, Integer pageSize) {
        return roleMapper.selectPage(pageNum,pageSize);
    }



}
