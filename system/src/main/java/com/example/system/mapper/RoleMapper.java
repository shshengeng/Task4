package com.example.system.mapper;


import com.example.system.model.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("SELECT * FROM sys_role where role_id = #{id}")
    Role selectById(Long id);

    @Select("SELECT * FROM sys_role")
    List<Role> selectAll();

    @Insert("""
I   INSERT INTO sys_role (role_id, name, level, description, data_scope, create_by, update_by, create_time, update_time)
    VALUES (#{roleId}
            #{name}, 
            #{level}, 
            #{description}, 
            #{dataScope},
            #{createBy}, 
            #{updateBy}, 
            #{createTime}, 
            #{updateTime})
    """)
    int insert(Role role);

    @Update("""
    UPDATE sys_role
    SET 
        role_id = #{roleId}, 
        name = #{name}, 
        level = #{level}, 
        description = #{description}, 
        data_scope = #{dataScope},
        create_by = #{createBy}, 
        update_by = #{updateBy}, 
        create_time = #{createTime}, 
        update_time = #{updateTime}
    WHERE role_id = #{roleId}
    """)
    int update(Role role);


    @Delete("DELETE FROM sys_role WHERE role_id = #{id}")
    int deleteById(Long id);


    @Select("SELECT * FROM sys_role limit #{pageNum},#{pageSize}")
    List selectPage(Integer pageNum, Integer pageSize);

}
