package br.com.nunesonline.todolistapi.repository;


import br.com.nunesonline.todolistapi.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String>{
    
    public Optional<List<Comment>> findByCommentBy(String id);
    
    public Optional<List<Comment>> findByCommentAtTask(String id);
        
}
