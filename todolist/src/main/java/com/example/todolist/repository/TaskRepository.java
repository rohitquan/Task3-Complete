package com.example.todolist.repository;

import com.example.todolist.dto.TaskDTO;
import com.example.todolist.model.TaskModel;

import java.util.List;

public interface TaskRepository {
    void saveTask(TaskDTO task);
    List<TaskModel> findTasksByUserId(int userId,int page,int size);
    TaskModel findTasksById(int taskId,int userId);
    boolean updateTask(int taskId,TaskDTO task);
    boolean deleteTask(int taskId,int userId);
    List<TaskModel> findAllTasks();
}
