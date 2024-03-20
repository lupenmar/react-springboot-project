package com.lupenmar.springtodo.controller;

import com.lupenmar.springtodo.model.Task;
import com.lupenmar.springtodo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    public List<Task> findAllTask() {

        return service.findAllTask();
    }

    @PostMapping("create_task")
    public String createTask(@RequestBody Task task, @RequestParam Long userId) {
        service.createTask(task, userId);
        return "Task successfully created";
    }

    @GetMapping("/user/{username}")
    public List<Task> findByUser(@PathVariable String username) {
        return service.findByUserUsername(username);
    }

    @GetMapping("/{taskId}")
    public Task findById(@PathVariable Long taskId) {
        return service.findById(taskId);
    }

    @PutMapping("update_task")
    public Task updateTask(@RequestBody Task task) {
        return service.updateTask(task);
    }

    @DeleteMapping("delete_task/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        service.deleteTask(taskId);
    }

}
