package br.com.nunesonline.todolistapi.service;

import br.com.nunesonline.todolistapi.config.security.JwtTokenUtil;
import br.com.nunesonline.todolistapi.entity.User;
import br.com.nunesonline.todolistapi.repository.UserRepository;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JwtTokenUtil jwtTokenService;
    @Autowired
    private UserRepository userRepository;


    public User verifyLogin(String login, String passwd)
            throws NoSuchAlgorithmException, Exception {
        if (Objects.isNull(login) || Objects.isNull(passwd)) {
            throw new Exception("Login/passwd must be informed");
        }

        final User user = (User) this.userRepository.findByLogin(login);

        //TODO apply criptography
        if (!user.getPasswd().equals(passwd)) {
            throw new Exception("Invalid password.");
        }
        user.setLastLogin(new Date());
        this.userRepository.save(user);
        
        return user;
    }
    
    public void validateToken(String token) {
        if (token == null) {
            logger.info("'validateToken' Token JWT not found");
            throw new SecurityException("User must auth");
        }
        if (this.jwtTokenService.isTokenExpired(token)) {
            logger.info("'validateToken' JWT token expired or invalid");
            throw new SecurityException("User auth expired or invalid");
        }
    }

}
