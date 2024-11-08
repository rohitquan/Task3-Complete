package com.example.todolist.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskModelRowMapper implements RowMapper<TaskModel> {

    @Override
    public TaskModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        TaskModel task = new TaskModel();
        task.setId(rs.getInt("id"));
        task.setUserId(rs.getInt("user_id"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setCompleted(rs.getBoolean("completed"));
        return task;
    }
}
