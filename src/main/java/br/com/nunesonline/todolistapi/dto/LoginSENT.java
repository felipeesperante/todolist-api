package br.com.nunesonline.todolistapi.dto;

import lombok.Data;

@Data
public class LoginSENT extends DefaultSENT{
    
    private String login;
    private String passwd;
    
}
