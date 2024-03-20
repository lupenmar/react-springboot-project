package com.lupenmar.springtodo.service;

import com.lupenmar.springtodo.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAllTask();

    Task createTask(Task task, Long userId);

    List<Task> findByUserUsername(String username);

    Task updateTask(Task task);

    Task findById(Long id);

    void deleteTask(Long taskId);

}
