package com.example.system.mapper;

import com.example.system.model.Dict;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface DictMapper {

    @Select("SELECT * FROM sys_dict where dict_id = #{id}")
    Dict selectById(Long id);

    @Select("SELECT * FROM sys_dict")
    List<Dict> selectAll();

    @Insert("""
I   INSERT INTO sys_dict (dict_id, name, description, create_by, update_by, create_time, update_time)
    VALUES (#{dictId}, 
            #{name}, 
            #{description}, 
            #{createBy}, 
            #{updateBy}, 
            #{createTime}, 
            #{updateTime})
    """)
    int insert(Dict dict);

    @Update("""
    UPDATE sys_dict
    SET 
        dict_id = #{dictId}, 
        name = #{name}, 
        description = #{description}, 
        create_by = #{createBy}, 
        update_by = #{updateBy}, 
        create_time = #{createTime}, 
        update_time = #{updateTime}
    WHERE dict_id = #{dictId}
    """)
    int update(Dict dict);


    @Delete("DELETE FROM sys_dict WHERE dict_id = #{id}")
    int deleteById(Long id);


    @Select("SELECT * FROM sys_dict limit #{pageNum},#{pageSize}")
    List selectPage(Integer pageNum, Integer pageSize);

}
