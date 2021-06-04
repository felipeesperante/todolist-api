package br.com.nunesonline.todolistapi.repository;

import br.com.nunesonline.todolistapi.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    public Optional<User> findByLogin(String login);
    
}
