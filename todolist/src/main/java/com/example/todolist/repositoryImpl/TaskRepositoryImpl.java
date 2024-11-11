package com.example.todolist.repositoryImpl;

import com.example.todolist.dto.TaskDTO;
import com.example.todolist.model.TaskModel;
import com.example.todolist.model.TaskModelRowMapper;
import com.example.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveTask(TaskDTO task) {
        String sql = "INSERT INTO tasks (user_id, title, description, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, task.getUserId(), task.getTitle(), task.getDescription(), task.getStatus());
    }

    @Override
    public List<TaskModel> findTasksByUserId(int userId, int page, int size) {
        String sql = "SELECT * FROM tasks WHERE user_id = ? LIMIT ? OFFSET ?";
        int offset = page * size;
        return jdbcTemplate.query(sql, new TaskModelRowMapper(), userId, size, offset);
    }

    // Method to get all tasks (for Admin to see all users' tasks)
    public List<TaskModel> findAllTasks() {
        String sql = "SELECT id, title, description, completed, user_id FROM tasks";
        // RowMapper for Mapping the query result to TaskModel
        return jdbcTemplate.query(sql,new TaskModelRowMapper());
    }

    @Override
    public TaskModel findTasksById(int taskId,int userId) {
        String sql = "SELECT * FROM tasks WHERE id = ? AND user_id";
        List<TaskModel> taskModels = jdbcTemplate.query(sql, new TaskModelRowMapper(), taskId, userId);
        return taskModels.isEmpty() ? null : taskModels.getFirst();
    }

    @Override
    public boolean updateTask(int taskId,TaskDTO task) {
        String sql = "UPDATE tasks SET title = ?, description = ?, status = ? WHERE id = ? AND user_id = ?";
        int rows = jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getStatus(), taskId,task.getUserId());
        return rows > 0;
    }

    @Override
    public boolean deleteTask(int taskId, int userId) {
        String sql = "DELETE FROM tasks WHERE id = ? AND user_id= ? ";
        int rows = jdbcTemplate.update(sql, taskId, userId);
        return rows>0;
    }

}
