package com.example.logging.mapper;

import com.example.logging.model.LogEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogEntryMapper {

    @Insert("INSERT INTO log_entry (timestamp, level, class_name, method_name, message) " +
            "VALUES (#{timestamp}, #{level}, #{className}, #{methodName}, #{message})")
    void insert(LogEntry logEntry);
}
