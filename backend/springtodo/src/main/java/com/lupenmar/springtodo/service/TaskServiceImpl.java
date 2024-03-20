package com.lupenmar.springtodo.service;

import com.lupenmar.springtodo.exception.NotFoundException;
import com.lupenmar.springtodo.exception.ValidationException;
import com.lupenmar.springtodo.model.Task;
import com.lupenmar.springtodo.model.User;
import com.lupenmar.springtodo.repository.TaskRepository;
import com.lupenmar.springtodo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Primary
public class TaskServiceImpl implements TaskService {

    private TaskRepository repository;
    private final UserRepository userRepository;

    @Override
    public List<Task> findAllTask() {
        return repository.findAll();
    }

    @Override
    public Task createTask(Task task, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        System.out.println(user.getUsername());

        task.setUser(user);

        if (task.getTitle().length() < 3) {
            throw new ValidationException("Title must be longer than 3 characters");
        }

        if (task.getDescription().length() < 10) {
            throw new ValidationException("Description must be longer than 10 characters");
        }

        return repository.save(task);
    }

    @Override
    public List<Task> findByUserUsername(String username) {
        return repository.findByUserUsername(username);
    }

    @Override
    public Task updateTask(Task task) {
        Optional<Task> existingTaskOptional = repository.findById(task.getId());

        if (existingTaskOptional.isEmpty()) {
            throw new NotFoundException("Task not found");
        }

        Task existingTask = existingTaskOptional.get();

        System.out.println("id = " + task.getId() + " Title =  " + task.getTitle() + " dis = " + task.getDescription());

        if (task.getTitle() != null) {
            if (task.getTitle().length() < 3) {
                throw new ValidationException("Title must be longer than 3 characters");
            }
            existingTask.setTitle(task.getTitle());
        } else {
            throw new ValidationException("Title cannot be null");
        }

        if (task.getDescription() != null) {
            if (task.getDescription().length() < 10) {
                throw new ValidationException("Description must be longer than 10 characters");
            }
            existingTask.setDescription(task.getDescription());
        } else {
            throw new ValidationException("Description cannot be null");
        }

        return repository.save(existingTask);
    }

    @Override
    public Task findById(Long taskId) {
        return repository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found"));
    }

    @Override
    public void deleteTask(Long taskId) {
        repository.deleteById(taskId);
    }
}
