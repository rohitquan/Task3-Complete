package com.example.todolist.controller;

import com.example.todolist.dto.TaskDTO;
import com.example.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Creates a new Task
    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    // Gets All tasks for authenticated user(with pagination)
    @GetMapping("/user")
    public ResponseEntity<?> getUserTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return taskService.getUserTasks(page,size);
    }

    // Get all tasks for admin (all users tasks)
    @GetMapping("/admin")
    public ResponseEntity<?> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Get a specific task by ID
    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable int taskId) {
        return taskService.getTaskById(taskId);
    }

    // Get a specific task by ID
    @PutMapping("/update/{taskId}")
    public ResponseEntity<?> updateTask(
            @PathVariable int taskId,
            @RequestBody TaskDTO taskDTO) {
        return taskService.updateTask(taskId, taskDTO);
    }

    // Delete a task by ID
    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable int taskId) {
        return taskService.deleteTask(taskId);
    }
}
