package br.com.nunesonline.todolistapi.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DefaultSENT {
    @NotNull
    private String login;
    @NotNull
    private String passwd;
    
}
