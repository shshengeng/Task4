package com.example.tools.mapper;

import com.example.tools.model.ToolLocalStorage;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ToolLocalStorageMapper {

    @Insert("INSERT INTO tool_local_storage (real_name, name, suffix, path, type, size, create_by, update_by, create_time, update_time) VALUES (#{realName}, #{name}, #{suffix}, #{path}, #{type}, #{size}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "storageId")
    void insert(ToolLocalStorage toolLocalStorage);

    @Select("SELECT * FROM tool_local_storage WHERE storage_id = #{storageId}")
    ToolLocalStorage selectById(Long storageId);

    @Select("SELECT * FROM tool_local_storage LIMIT #{offset}, #{limit}")
    List<ToolLocalStorage> selectPaged(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM tool_local_storage")
    int count();

    @Select("SELECT * FROM tool_local_storage")
    List<ToolLocalStorage> selectAll();
}
