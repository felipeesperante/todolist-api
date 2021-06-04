package br.com.nunesonline.todolistapi.service;

import br.com.nunesonline.todolistapi.dto.UserRET;
import br.com.nunesonline.todolistapi.dto.UserSENT;
import br.com.nunesonline.todolistapi.entity.User;
import br.com.nunesonline.todolistapi.repository.UserRepository;
import br.com.nunesonline.todolistapi.util.Generator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(TodoListService.class);

    @Autowired
    private UserRepository userRepository;

    public void saveNewUser(String login, String passwd, String email)
            throws Exception {
        try {

            Optional<User> existingUser = userRepository.findByLogin(login);
            if (existingUser.isPresent()) {
                logger.info("Informed user: " + login + " already has account.");
                throw new Exception("User already exists");
            }

            User newUser = new User();
            newUser.setId(Generator.generateId());
            newUser.setEmail(email);
            newUser.setLogin(login);
            newUser.setPasswd(passwd); //TODO apply crypto
            newUser.setLastLogin(new Date());
            newUser.setLogged(false);
            userRepository.save(newUser);
            logger.info("User: " + login + " has been created.");
        } catch (Exception e) {
            logger.info("Error creating user: " + login + ". Cause: " + e.getMessage());
            throw new Exception("Create user failed. Cause:" + e.getMessage());
        }
    }

    public void updateUser(UserSENT env) throws Exception {
        try {
            Optional<User> user = this.userRepository.findByLogin(env.getLogin());
            if (user.isPresent()) {
                User newUser = user.get();
                newUser.setEmail(env.getEmail());
                newUser.setPasswd(env.getPasswd()); //TODO apply crypto
                newUser.setLastLogin(new Date());
                newUser.setLogged(true);
                userRepository.save(newUser);
                logger.info("User: " + env.getLogin() + " has been updated.");
            } else {
                logger.info("User: " + env.getLogin() + " is not logged.");
                throw new Exception("User not logged");
            }
        } catch (Exception e) {
            logger.info("Error creating user: " + env.getLogin() + ". Cause: " + e.getMessage());
            throw new Exception("Update user failed. Cause:" + e.getMessage());
        }

    }

    public List<UserRET> findAll() {
        List<UserRET> resultList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (User u : userList) {
            UserRET user = new UserRET();
            user.setId(u.getId());
            user.setEmail(u.getEmail());
            user.setLogin(u.getLogin());
            resultList.add(user);
        }
        return resultList;
    }

    public UserRET findOneById(String id) throws Exception{
        Optional<User> u = this.userRepository.findById(id);
        if (u.isPresent()) {
            UserRET user = new UserRET();
            user.setId(u.get().getId());
            user.setEmail(u.get().getEmail());
            user.setLogin(u.get().getLogin());
            return user;
        } else 
            throw new Exception("User not found");
    }

}
