package com.example.todolist.repository;

import com.example.todolist.dto.TaskDTO;
import com.example.todolist.model.TaskModel;

import java.util.List;

public interface TaskRepository {
    void saveTask(TaskDTO task);
    List<TaskModel> findTasksByUserId(int userId,int page,int size);
    void updateTask(int id,TaskDTO task);
    TaskModel findById(int id);
    void deleteTask(int id);
}
