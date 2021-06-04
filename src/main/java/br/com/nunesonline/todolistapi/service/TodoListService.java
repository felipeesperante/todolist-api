package br.com.nunesonline.todolistapi.service;

import br.com.nunesonline.todolistapi.dto.CommentSENT;
import br.com.nunesonline.todolistapi.dto.TaskRET;
import br.com.nunesonline.todolistapi.dto.TaskSENT;
import br.com.nunesonline.todolistapi.dto.UserRET;
import br.com.nunesonline.todolistapi.dto.UserSENT;
import br.com.nunesonline.todolistapi.entity.Comment;
import br.com.nunesonline.todolistapi.entity.Task;
import br.com.nunesonline.todolistapi.entity.User;
import br.com.nunesonline.todolistapi.repository.CommentRepository;
import br.com.nunesonline.todolistapi.repository.TaskRepository;
import br.com.nunesonline.todolistapi.repository.UserRepository;
import br.com.nunesonline.todolistapi.util.DateUtil;
import br.com.nunesonline.todolistapi.util.Generator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {

    private final Logger logger = LoggerFactory.getLogger(TodoListService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CommentRepository commentRepository;

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
            newUser.setLastLogin(DateUtil.formatToDtHrMinSec(new Date()));
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
                newUser.setEmail(env.getPasswd());
                userRepository.save(newUser);
                logger.info("User: " + env.getLogin() + " has been updated.");
            } else {
                logger.info("User: " + env.getLogin() + " is not found.");
                throw new Exception("User not found");
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

    public UserRET findOneById(String id) throws Exception {
        Optional<User> u = this.userRepository.findById(id);
        if (u.isPresent()) {
            UserRET user = new UserRET();
            user.setId(u.get().getId());
            user.setEmail(u.get().getEmail());
            user.setLogin(u.get().getLogin());
            Optional<List<Task>> listOfTasks = this.taskRepository.findByCreatedBy(id);
            if (listOfTasks.isEmpty()) {
                user.setTasks(null);
            } else {
                user.setTasks(listOfTasks.get());
            }
            return user;
        } else {
            throw new Exception("User not found");
        }
    }

    public void saveNewTask(TaskSENT env) throws Exception {
        try {
            Optional<User> user = this.userRepository.findByLogin(env.getLogin());
            if (user.isPresent()) {
                Task newTask = new Task();
                newTask.setCompleted(env.isCompleted());
                newTask.setCreatedAt(DateUtil.formatToDtHrMinSec(new Date()));
                newTask.setCreatedBy(user.get().getId());
                newTask.setDescription(env.getDescription());
                newTask.setHasComments(false);
                newTask.setId(Generator.generateId());
                newTask.setLastModified(null);
                if (env.getScheduledTo() != null) {
                    newTask.setScheduledTo(DateUtil.formatStrToDtHrMinSec(env.getScheduledTo()));
                }
                newTask.setTimeSpent(env.getTimeSpent());
                taskRepository.save(newTask);
                logger.info("Task has been created.");
            } else {
                logger.info("User: " + env.getLogin() + " is not found.");
                throw new Exception("User not found");
            }
        } catch (Exception e) {
            logger.info("Error creating task: " + env.getLogin() + ". Cause: " + e.getMessage());
            throw new Exception("Creat task failed. Cause:" + e.getMessage());
        }
    }

    public void editTask(TaskSENT env) throws Exception {
        try {
            Optional<User> user = this.userRepository.findByLogin(env.getLogin());
            if (user.isPresent()) {
                Optional<Task> savedTask = taskRepository.findById(env.getId());
                if (savedTask.isPresent()) {
                    savedTask.get().setCompleted(env.isCompleted());
                    savedTask.get().setDescription(env.getDescription());
                    savedTask.get().setLastModified(DateUtil.formatToDtHrMinSec(new Date()));
                    savedTask.get().setScheduledTo(DateUtil.formatStrToDtHrMinSec(env.getScheduledTo()));
                    savedTask.get().setTimeSpent(env.getTimeSpent());
                    taskRepository.save(savedTask.get());
                    logger.info("Task has been edited.");
                } else {
                    logger.info("Task: " + env.getId() + " does not exist.");
                    throw new Exception("Task not found");
                }
            } else {
                logger.info("User: " + env.getLogin() + " is not found.");
                throw new Exception("User not found");
            }
        } catch (Exception e) {
            logger.info("Error creating task: " + env.getLogin() + ". Cause: " + e.getMessage());
            throw new Exception("Update task failed. Cause:" + e.getMessage());
        }
    }

    public void deleteTask(TaskSENT env) throws Exception {
        try {
            Optional<User> user = this.userRepository.findByLogin(env.getLogin());
            if (user.isPresent()) {
                Optional<Task> savedTask = taskRepository.findById(env.getId());
                if (savedTask.isPresent()) {
                    taskRepository.delete(savedTask.get());
                    logger.info("Task has been deleted.");
                } else {
                    logger.info("Task: " + env.getId() + " does not exist.");
                    throw new Exception("Task not found");
                }
            } else {
                logger.info("User: " + env.getLogin() + " is not found.");
                throw new Exception("User not found");
            }
        } catch (Exception e) {
            logger.info("Error creating task: " + env.getLogin() + ". Cause: " + e.getMessage());
            throw new Exception("Update task failed. Cause:" + e.getMessage());
        }
    }

    public TaskRET findTaskById(String id) throws Exception {
        Optional<Task> u = this.taskRepository.findById(id);
        if (u.isPresent()) {
            TaskRET task = new TaskRET();
            task.setCompleted(u.get().isCompleted());
            task.setCreatedAt(DateUtil.formatDtHrMinSec(u.get().getCreatedAt()));
            task.setCreatedBy(u.get().getCreatedBy());
            task.setDescription(u.get().getDescription());
            task.setId(u.get().getId());
            task.setLastModified(DateUtil.formatDtHrMinSec(u.get().getLastModified()));
            task.setScheduledTo(DateUtil.formatDtHrMinSec(u.get().getScheduledTo()));
            task.setTimeSpent(u.get().getTimeSpent());
            Optional<List<Comment>> listOfComments = this.commentRepository.findByCommentAtTask(id);
            if (listOfComments.isEmpty()) {
                task.setHasComments(false);
                task.setComments(null);
            } else {
                task.setHasComments(true);
                task.setComments(listOfComments.get());
            }
            return task;
        } else {
            throw new Exception("Task not found");
        }
    }

    public List<TaskRET> findTaskByUserId(String id) throws Exception {
        List<TaskRET> ret = new ArrayList<>();
        Optional<User> u = this.userRepository.findById(id);
        if (u.isPresent()) {
            Optional<List<Task>> listOfTasks = this.taskRepository.findByCreatedBy(id);
            if (listOfTasks.isPresent()) {
                logger.info("present");
                List<Task> tasks = listOfTasks.get();
                for (Task t : tasks) {
                    logger.info("task t: " + t.getId());
                    TaskRET task = new TaskRET();
                    task.setCompleted(t.isCompleted());
                    if (t.getCreatedAt() != null) {
                        task.setCreatedAt(DateUtil.formatDtHrMinSec(t.getCreatedAt()));
                    }
                    task.setCreatedBy(t.getCreatedBy());
                    task.setDescription(t.getDescription());
                    task.setId(t.getId());
                    if (t.getLastModified() != null) {
                        task.setLastModified(DateUtil.formatDtHrMinSec(t.getLastModified()));
                    }
                    if (t.getScheduledTo() != null) {
                        task.setScheduledTo(DateUtil.formatDtHrMinSec(t.getScheduledTo()));
                    }
                    task.setTimeSpent(t.getTimeSpent());
                    Optional<List<Comment>> listOfComments = this.commentRepository.findByCommentAtTask(id);
                    if (listOfComments.isEmpty()) {
                        task.setHasComments(false);
                        task.setComments(null);
                    } else {
                        task.setHasComments(true);
                        task.setComments(listOfComments.get());
                    }
                    ret.add(task);
                }
            } else {
                logger.info("Not present");
                ret = null;
            }
        }
        return ret;
    }

    public void saveNewComment(CommentSENT env) throws Exception {
        Optional<User> user = this.userRepository.findByLogin(env.getLogin());
        if (user.isPresent()) {
            Comment newComment = new Comment();
            newComment.setComment(env.getComment());
            newComment.setCommentAtTask(env.getCommentAtTask());
            newComment.setCommentBy(env.getCommentBy());
            newComment.setId(Generator.generateId());
            newComment.setLastModified(null);
            commentRepository.save(newComment);

            Optional<Task> taskSaved = taskRepository.findById(env.getCommentAtTask());
            if (taskSaved.isPresent()) {
                taskSaved.get().setHasComments(true);
                taskRepository.save(taskSaved.get());
            }
        }
    }

    public void updateComment(CommentSENT env) throws Exception {
        Optional<User> user = this.userRepository.findByLogin(env.getLogin());
        if (user.isPresent()) {
            Optional<Comment> commentSaved = commentRepository.findById(env.getId());
            if (commentSaved.isPresent()) {
                commentSaved.get().setComment(env.getComment());
                commentSaved.get().setLastModified(DateUtil.formatToDtHrMinSec(new Date()));
            }
            commentRepository.save(commentSaved.get());
        }
    }

}
