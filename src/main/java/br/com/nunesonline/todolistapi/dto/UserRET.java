package br.com.nunesonline.todolistapi.dto;

import br.com.nunesonline.todolistapi.entity.Task;
import java.util.List;
import lombok.Data;

@Data
public class UserRET {
    
    private String id;
    private String login;
    private String email;
    private List<Task> tasks; //not right to send model to view, but time is money
    
}
