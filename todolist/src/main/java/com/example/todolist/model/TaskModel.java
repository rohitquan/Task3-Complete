package com.example.todolist.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {
    private int id;
    private int userId;
    private String title;
    private String description;
    private String status;
}
