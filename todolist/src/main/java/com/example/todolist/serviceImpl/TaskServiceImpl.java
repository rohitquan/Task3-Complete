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
    public ResponseEntity<?> getUserTasks(int page, int size) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<TaskModel> tasks = taskRepository.findTasksByUserId(username.hashCode(),page,size);
        return ResponseEntity.ok(tasks);
    }

    public ResponseEntity<?> getTaskById(int taskId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        TaskModel taskModel=taskRepository.findTasksById(taskId,username.hashCode());
        if(taskModel == null){
            return ResponseEntity.status(403).body("Access denied or task not found");
        }
        return ResponseEntity.ok(taskModel);
    }

    @Override
    public ResponseEntity<?> getAllTasks() {
        //check if the user is an admin
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            // Admin can access all tasks
            List<TaskModel> tasks = taskRepository.findAllTasks();
            return ResponseEntity.ok(tasks);
        } else {
            // Return an error if not Admin
            return ResponseEntity.status(403).body("Access denied. Admins only.");
        }

    }


    @Override
    public ResponseEntity<?> updateTask(int taskId, TaskDTO taskDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        taskDTO.setUserId(username.hashCode());
        boolean updated = taskRepository.updateTask(taskId,taskDTO);
        if(!updated){
            return ResponseEntity.status(403).body("Access denied or task not found");
        }
        return ResponseEntity.ok("Task updated successfully");
    }

    @Override
    public ResponseEntity<?> deleteTask(int taskId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean deleted = taskRepository.deleteTask(taskId,username.hashCode());
        if(!deleted){
            return ResponseEntity.status(403).body("Access denied or task not found");
        }
        return ResponseEntity.ok("Task deleted successfully");
    }
}
