package com.example.todolist.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelUser {
    private int id;
    private String username;
    private String password;
    private String role; // "ROLE_USER" or "ROLE_ADMIN"
}
