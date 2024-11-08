package com.example.todolist.model;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModelRowMapper implements RowMapper<ModelUser> {

    @Override
    public ModelUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        ModelUser user = new ModelUser();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        return user;
    }
}
