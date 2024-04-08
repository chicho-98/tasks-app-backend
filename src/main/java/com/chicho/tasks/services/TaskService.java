package com.chicho.tasks.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chicho.tasks.model.Task;
import com.chicho.tasks.repositories.TaskRepository;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
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
