package com.example.system.mapper;
import com.example.system.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM sys_user where user_id = #{id}")
    User selectById(Long id);

    @Select("SELECT * FROM sys_user")
    List<User> selectAll();

    @Insert("""
I   INSERT INTO sys_user (user_id, dept_id, username, nickname, gender, phone, email, avatar_name, avatar_path, password, is_admin, enabled, create_by, update_by, pwd_reset_time, create_time, update_time)
    VALUES (#{userId}, 
            #{deptId}, 
            #{userName}, 
            #{nickName}, 
            #{gender},
            #{phone}, 
            #{email}, 
            #{avatarName}, 
            #{password}, 
            #{isAdmin}, 
            #{enabled}, 
            #{createBy}, 
            #{updateBy}, 
            #{pwdResetTime}, 
            #{createTime}, 
            #{updateTime})
    """)
    int insert(User user);

    @Update("""
    UPDATE sys_user
    SET dept_id = #{deptId},
        username = #{userName},
        nickname = #{nickName},
        gender = #{gender},
        phone = #{phone},
        email = #{email},
        avatar_name = #{avatarName},
        password = #{password},
        is_admin = #{isAdmin},
        enabled = #{enabled},
        create_by = #{createBy},
        update_by = #{updateBy},
        pwd_reset_time = #{pwdResetTime},
        create_time = #{createTime},
        update_time = #{updateTime}
    WHERE user_id = #{userId}
    """)
    int update(User user);


    @Delete("DELETE FROM sys_user WHERE user_id = #{id}")
    int deleteById(Long id);


    @Select("SELECT * FROM sys_user limit #{pageNum},#{pageSize}")
    List selectPage(Integer pageNum, Integer pageSize);
}
