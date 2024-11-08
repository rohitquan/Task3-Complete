package com.example.todolist.serviceImpl;

import com.example.todolist.dto.TaskDTO;
import com.example.todolist.model.TaskModel;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public ResponseEntity<?> createTask(TaskDTO taskDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        taskDTO.setUserId(username.hashCode()); // For simplicity, hash username as user ID
        taskRepository.saveTask(taskDTO);
        return ResponseEntity.ok("Task created successfully");
    }

    @Override
    public ResponseEntity<?> getTasks(int page, int size) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<TaskModel> tasks = taskRepository.findTasksByUserId(username.hashCode(),page,size);
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<?> updateTask(int id, TaskDTO taskDTO) {
        taskRepository.updateTask(id,taskDTO);
        return ResponseEntity.ok("Task updated successfully");
    }

    @Override
    public ResponseEntity<?> deleteTask(int id) {
        taskRepository.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
