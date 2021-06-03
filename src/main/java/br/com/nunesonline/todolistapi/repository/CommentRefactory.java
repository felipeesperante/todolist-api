package br.com.nunesonline.todolistapi.repository;


import br.com.nunesonline.todolistapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRefactory extends JpaRepository<Comment, String>{
    
}
