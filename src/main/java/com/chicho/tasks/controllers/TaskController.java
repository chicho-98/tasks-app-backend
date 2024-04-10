package com.chicho.tasks.controllers;

import java.util.List;

import com.chicho.tasks.model.user.User;
import com.chicho.tasks.services.TokenService;
import com.chicho.tasks.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chicho.tasks.model.Task;
import com.chicho.tasks.services.TaskService;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TokenService tokenService;

    public TaskController(TaskService taskService, TokenService tokenService) {
        this.taskService = taskService;
        this.tokenService = tokenService;
    }

    @GetMapping("")
    public List<Task> getAllTasks(HttpServletRequest request) {
        String token = tokenService.recoverTokenFromRequest(request);
        String email = tokenService.getSubjectFromToken(token);
        return taskService.getAllTasksByEmail(email);
    }

    @PostMapping("")
    public void createTask(@RequestBody Task task, HttpServletRequest request) {
        String token = tokenService.recoverTokenFromRequest(request);
        String email = tokenService.getSubjectFromToken(token);
        taskService.addTask(email, task);
    }

    @PutMapping("/{id}")
    public Task editTask(@RequestBody Task editedTask, @PathVariable Long id) {
        return taskService.editTask(editedTask, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
