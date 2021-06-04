package br.com.nunesonline.todolistapi.dto;

import br.com.nunesonline.todolistapi.entity.Comment;
import java.util.List;
import lombok.Data;

@Data
public class TaskRET {
    
    
    private String id;
    
    private String description;
    
    private String createdAt;
    
    private String lastModified;
    
    private boolean hasComments;
    
    private Integer timeSpent;
    
    private boolean isCompleted;
    
    private String scheduledTo;
    
    private String createdBy;
    
    private List<Comment> comments; //not right to send model to view, but time is money
    
}
