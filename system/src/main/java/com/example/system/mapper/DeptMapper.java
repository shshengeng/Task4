package com.example.system.mapper;

import com.example.system.model.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("SELECT * FROM sys_dept where dept_id = #{id}")
    Dept selectById(Long id);

    @Select("SELECT * FROM sys_dept")
    List<Dept> selectAll();

    @Insert("""
I   INSERT INTO sys_dept (dept_id, pid, sub_count, name, dept_sort, enabled, create_by, update_by, create_time, update_time)
    VALUES (#{deptId}, 
            #{pid}, 
            #{subCount}, 
            #{name}, 
            #{deptSort},
            #{enabled}, 
            #{createBy}, 
            #{updateBy}, 
            #{createTime}, 
            #{updateTime})
    """)
    int insert(Dept dept);

    @Update("""
    UPDATE sys_dept
    SET 
        dept_id = #{deptId}, 
        pid = #{pid}, 
        sub_count = #{subCount}, 
        name = #{name}, 
        dept_sort = #{deptSort},
        enabled = #{enabled}, 
        create_by = #{createBy}, 
        update_by = #{updateBy}, 
        create_time = #{createTime}, 
        update_time = #{updateTime}
    WHERE dept_id = #{deptId}
    """)
    int update(Dept dept);


    @Delete("DELETE FROM sys_dept WHERE dept_id = #{id}")
    int deleteById(Long id);


    @Select("SELECT * FROM sys_dept limit #{pageNum},#{pageSize}")
    List selectPage(Integer pageNum, Integer pageSize);

}
