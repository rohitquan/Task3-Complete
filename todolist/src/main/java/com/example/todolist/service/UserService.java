package com.example.todolist.service;

import com.example.todolist.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> register(UserDTO userDTO);
    ResponseEntity<?> login(UserDTO userDTO);
}
