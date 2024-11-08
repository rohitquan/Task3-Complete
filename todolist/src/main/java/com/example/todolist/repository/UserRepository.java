package com.example.todolist.repository;

import com.example.todolist.dto.UserDTO;
import com.example.todolist.model.ModelUser;

public interface UserRepository {
    ModelUser findByUsername(String username);
    void saveUser(UserDTO user);
}

