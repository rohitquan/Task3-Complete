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
        String sql = "INSERT INTO tasks (user_id, title, description, completed) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, task.getUserId(), task.getTitle(), task.getDescription(), task.isCompleted());
    }

    @Override
    public List<TaskModel> findTasksByUserId(int userId, int page, int size) {
        String sql = "SELECT * FROM tasks WHERE user_id = ? LIMIT ? OFFSET ?";
        int offset = page * size;
        return jdbcTemplate.query(sql, new TaskModelRowMapper(), userId, size, offset);
    }

    @Override
    public TaskModel findById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new TaskModelRowMapper(), id);
    }

    @Override
    public void updateTask(int id,TaskDTO task) {
        String sql = "UPDATE tasks SET title = ?, description = ?, completed = ? WHERE id = ?";
        jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.isCompleted(), id);
    }

    @Override
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
