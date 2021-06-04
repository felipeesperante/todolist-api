package br.com.nunesonline.todolistapi.rest;

import br.com.nunesonline.todolistapi.dto.*;
import br.com.nunesonline.todolistapi.service.TodoListService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping(value = "/todo", produces = APPLICATION_JSON_VALUE)
public class TodoListApiREST {

    private final Logger logger = LoggerFactory.getLogger(TodoListApiREST.class);

    @Autowired
    public TodoListService todoListService;

    /**
     * ENDPOINTS USER
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public ResponseEntity<List<UserRET>> getUsers() {
        List<UserRET> ret = todoListService.findAll();
        return new ResponseEntity<>(ret, OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity<UserRET> getOneUser(@PathVariable String id) {
        try {
            UserRET ret = todoListService.findOneById(id);
            return new ResponseEntity<>(ret, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseEntity<DefaultRET> createUser(@Valid @RequestBody UserSENT env) {
        DefaultRET ret = new DefaultRET();
        try {
            //validate login/passwd before a service call
            todoListService.saveNewUser(env.getLogin(), env.getPasswd(), env.getEmail());
            ret.setCd("200");
            ret.setMsg("Login Accepted");
            return new ResponseEntity<>(ret, OK);
        } catch (Exception e) {
            ret.setCd("500");
            ret.setMsg(e.getMessage());
            return new ResponseEntity<>(ret, INTERNAL_SERVER_ERROR);
        }
        
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public ResponseEntity<DefaultRET> editUser(@Valid @RequestBody UserSENT env) {
        DefaultRET ret = new DefaultRET();
        try {
            todoListService.updateUser(env);
            ret.setCd("200");
            ret.setMsg("Edit Accepted");
        } catch (Exception e) {
            ret.setCd("500");
            ret.setMsg(e.getMessage());
            return new ResponseEntity<>(ret, INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ret, OK);
    }

    /**
     * ENDPOINTS TASKS
     */
    @RequestMapping(method = RequestMethod.GET, value = "/task/{id}")
    public ResponseEntity<TaskRET> getOneTask(@PathVariable String id) {
        try {
            TaskRET ret = todoListService.findTaskById(id);
            return new ResponseEntity<>(ret, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/task/user/{id}")
    public ResponseEntity<List<TaskRET>> getTasksByUser(@PathVariable String id) {
        try {
            List<TaskRET> ret = todoListService.findTaskByUserId(id);
            return new ResponseEntity<>(ret, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/task")
    public ResponseEntity<DefaultRET> createTask(@Valid @RequestBody TaskSENT env) {
        DefaultRET ret = new DefaultRET();
        try {
            todoListService.saveNewTask(env);
            ret.setCd("200");
            ret.setMsg("Task Accepted");
            return new ResponseEntity<>(ret, OK);
        } catch (Exception e) {
            ret.setCd("500");
            ret.setMsg(e.getMessage());
            return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
        }
        
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/task")
    public ResponseEntity<DefaultRET> editTask(@Valid @RequestBody TaskSENT env) {
        DefaultRET ret = new DefaultRET();
        try {
            todoListService.editTask(env);
            ret.setCd("200");
            ret.setMsg("Task Accepted");
            return new ResponseEntity<>(ret, OK);
        } catch (Exception e) {
            ret.setCd("500");
            ret.setMsg(e.getMessage());
            return new ResponseEntity<>(ret, INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/task")
    public ResponseEntity<DefaultRET> deleteTask(@Valid @RequestBody TaskSENT env) {
        DefaultRET ret = new DefaultRET();
        try {
            todoListService.deleteTask(env);
            ret.setCd("200");
            ret.setMsg("Task Accepted");
            return new ResponseEntity<>(ret, OK);
        } catch (Exception e) {
            ret.setCd("500");
            ret.setMsg(e.getMessage());
            return new ResponseEntity<>(ret, INTERNAL_SERVER_ERROR);
        }
        
    }

    /**
     * ENDPOINTS COMMENTS
     */
    @RequestMapping(method = RequestMethod.POST, value = "/comment")
    public ResponseEntity<DefaultRET> createComment(@Valid @RequestBody CommentSENT env) {
        DefaultRET ret = new DefaultRET();
        try {
            todoListService.saveNewComment(env);
            ret.setCd("200");
            ret.setMsg("Comment Accepted");
            return new ResponseEntity<>(ret, OK);
        } catch (Exception e) {
            ret.setCd("500");
            ret.setMsg(e.getMessage());
            return new ResponseEntity<>(ret, INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/comment")
    public ResponseEntity<DefaultRET> editComment(@Valid @RequestBody CommentSENT env) {
        DefaultRET ret = new DefaultRET();
        try {
            todoListService.updateComment(env);
            ret.setCd("200");
            ret.setMsg("Comment Accepted");
            return new ResponseEntity<>(ret, OK);
        } catch (Exception e) {
            ret.setCd("500");
            ret.setMsg(e.getMessage());
            return new ResponseEntity<>(ret, INTERNAL_SERVER_ERROR);
        }
    }
}
