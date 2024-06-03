package com.example.logging.mapper;

import com.example.system.model.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {

    @Select("select * from sys_log")
    List<Log> getAll();

    @Select("select * from sys_log where log_id = #{logId}")
    Log getById(Long logId);
}
