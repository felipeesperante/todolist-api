package br.com.nunesonline.todolistapi.dto;

import lombok.Data;

@Data
public class TaskSENT extends DefaultSENT{
    
    private String id;

    private String description;

    private Integer timeSpent;

    private boolean isCompleted;

    private String scheduledTo;

}
