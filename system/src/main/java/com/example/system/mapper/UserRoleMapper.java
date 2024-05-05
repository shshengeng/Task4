package com.example.system.mapper;


import com.example.system.model.UsersRoles;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    @Select("SELECT * FROM sys_users_roles where user_id = #{userId}")
    List<UsersRoles> getPermission(Long userId);


    @Insert("INSERT INTO sys_users_roles(user_id, role_id) values (#{userId}, #{roleId}) ")
    int savePermission(Long userId,Long roleId);

}
