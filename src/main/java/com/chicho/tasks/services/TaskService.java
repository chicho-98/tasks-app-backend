package com.chicho.tasks.services;

import java.util.List;

import com.chicho.tasks.model.user.User;
import org.springframework.stereotype.Service;

import com.chicho.tasks.model.Task;
import com.chicho.tasks.repositories.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public List<Task> getAllTasksByEmail(String email) {
        User user = (User) userService.getUserByEmail(email);
        return user.getTasks();
    }

    public void addTask(String email, Task task) {
        User user = (User) userService.getUserByEmail(email);
        user.addTask(task);
        userService.saveUser(user);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task editTask(Task editedTask, Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setName(editedTask.getName());
        task.setDescription(editedTask.getDescription());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
