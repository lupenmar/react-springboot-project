package com.lupenmar.springtodo.service;

import com.lupenmar.springtodo.model.Task;
import com.lupenmar.springtodo.repository.InMemoryTaskDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryTaskServiceImpl implements TaskService {

    private final InMemoryTaskDAO repository;

    @Override
    public List<Task> findAllTask() {
        return repository.findAllTask();
    }

    @Override
    public Task createTask(Task task, Long userId) {
        return repository.createTask(task);
    }

    @Override
    public List<Task> findByUserUsername(String username) {
        return repository.findByUser(username);
    }

    @Override
    public Task updateTask(Task task) {
        return repository.updateTask(task);
    }

    @Override
    public Task findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteTask(Long taskId) {
        repository.deleteTask(taskId);
    }
}
