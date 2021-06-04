package br.com.nunesonline.todolistapi.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserSENT extends DefaultSENT{
    @NotNull
    private String email;
    private String passwd;
}
