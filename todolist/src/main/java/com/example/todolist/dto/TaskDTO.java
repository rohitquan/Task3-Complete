package com.example.todolist.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private int userId;
    private String title;
    private String description;
    private String status;
}
