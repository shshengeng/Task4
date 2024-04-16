package com.example.system.service;

import com.example.system.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getUserByName(String name);
}
