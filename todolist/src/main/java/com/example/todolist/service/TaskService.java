package com.example.todolist.service;

import com.example.todolist.dto.TaskDTO;
import org.springframework.http.ResponseEntity;

public interface TaskService {
    ResponseEntity<?> createTask(TaskDTO taskDTO);
    ResponseEntity<?> getUserTasks(int page,int size);
    ResponseEntity<?> getTaskById(int taskId);
    ResponseEntity<?> updateTask(int taskId, TaskDTO taskDTO);
    ResponseEntity<?> deleteTask(int taskId);
    ResponseEntity<?> getAllTasks();
}
