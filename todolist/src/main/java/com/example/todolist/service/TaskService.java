package com.example.todolist.service;

import com.example.todolist.dto.TaskDTO;
import org.springframework.http.ResponseEntity;

public interface TaskService {
    ResponseEntity<?> createTask(TaskDTO taskDTO);
    ResponseEntity<?> getTasks(int page,int size);
    ResponseEntity<?> updateTask(int id, TaskDTO taskDTO);
    ResponseEntity<?> deleteTask(int id);
}
