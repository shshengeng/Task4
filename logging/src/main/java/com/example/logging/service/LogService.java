package com.example.logging.service;

import com.example.system.model.Log;

import java.util.List;

public interface LogService {

    List<Log> getAll();

    Log getById(Long logId);

    byte[] exportDataToExcel() throws Exception;
}
