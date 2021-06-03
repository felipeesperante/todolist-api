package br.com.nunesonline.todolistapi.repository;

import br.com.nunesonline.todolistapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, String>{
    
}
