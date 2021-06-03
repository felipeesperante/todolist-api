package br.com.nunesonline.todolistapi.rest;

import br.com.nunesonline.todolistapi.config.security.JwtTokenUtil;
import br.com.nunesonline.todolistapi.dto.CommentSENT;
import br.com.nunesonline.todolistapi.dto.DefaultSENT;
import br.com.nunesonline.todolistapi.dto.DefaultRET;
import br.com.nunesonline.todolistapi.dto.LoginRET;
import br.com.nunesonline.todolistapi.dto.LoginSENT;
import br.com.nunesonline.todolistapi.dto.TaskSENT;
import br.com.nunesonline.todolistapi.dto.UserRET;
import br.com.nunesonline.todolistapi.dto.UserSENT;
import br.com.nunesonline.todolistapi.entity.User;
import br.com.nunesonline.todolistapi.service.UserService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/todo", produces = APPLICATION_JSON_VALUE)
public class TodoListApiREST {

    private final Logger logger = LoggerFactory.getLogger(TodoListApiREST.class);

    @Autowired
    public UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * ENDPOINTS LOGIN
     */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<DefaultRET> login(@Valid @RequestBody LoginSENT loginENV) throws Exception {
        try {
            logger.info("'login' Autenticação do usuário: {}", loginENV.getLogin());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginENV.getLogin(), loginENV.getPasswd()));
            User user = userService.verifyLogin(loginENV.getLogin(), loginENV.getPasswd());
            LoginRET ret = new LoginRET();
            if (user != null) {
                ret.setJwtToken(jwtTokenUtil.generateToken(loginENV.getLogin()).getToken());
                ret.setCd("200");
                ret.setMsg("Login successful");
                return ResponseEntity.ok(ret);
            } else {
                logger.info("'login' Autenticação do usuário: {}", loginENV.getLogin());
                throw new Exception("INVALID_CREDENTIALS");
            }
        } catch (Exception e) {
            throw new Exception("API INTERNAL ERROR:: " + e.getMessage().toUpperCase());
        }
    }

    /**
     * ENDPOINTS USER
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public ResponseEntity<List<UserRET>> getUsers() {
        System.out.println("getUsers");
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity<UserRET> getOneUser() {
        System.out.println("getOneUser");
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseEntity<DefaultRET> creatUser(@Valid @RequestBody UserSENT env) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public ResponseEntity<DefaultRET> editUser(@Valid @RequestBody UserSENT env) {
        return null;
    }

    /**
     * ENDPOINTS TASKS
     */
    @RequestMapping(method = RequestMethod.GET, value = "/task/{id}")
    public ResponseEntity<DefaultRET> getOneTask() {
        //return one task
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/task/user/{id}")
    public ResponseEntity<DefaultRET> getTasksByUser() {
        //return one task
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/task")
    public ResponseEntity<DefaultRET> creatTask(@Valid @RequestBody TaskSENT env) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/task")
    public ResponseEntity<DefaultRET> editTask(@Valid @RequestBody TaskSENT env) {
        return null;
    }

    /**
     * ENDPOINTS COMMENTS
     */
    @RequestMapping(method = RequestMethod.GET, value = "/comment/task/{id}")
    public ResponseEntity<DefaultRET> getCommentsByTask() {
        //return all tasks from comment
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/comment")
    public ResponseEntity<DefaultRET> creatComment(@Valid @RequestBody CommentSENT env) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/comment")
    public ResponseEntity<DefaultRET> editComment(@Valid @RequestBody CommentSENT env) {
        return null;
    }
}
