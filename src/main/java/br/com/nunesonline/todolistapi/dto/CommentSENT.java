package br.com.nunesonline.todolistapi.dto;

import lombok.Data;

@Data
public class CommentSENT extends DefaultSENT{

    private String id;
    private String comment;
    private String commentBy;
    private String commentAtTask;

}
