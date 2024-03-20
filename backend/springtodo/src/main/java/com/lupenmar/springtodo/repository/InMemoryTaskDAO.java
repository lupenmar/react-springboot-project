package com.lupenmar.springtodo.repository;

import com.lupenmar.springtodo.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

@Repository
public class InMemoryTaskDAO {

    private final List<Task> TASK = new ArrayList<>();

    public List<Task> findAllTask() {
        return TASK;
    }

    public Task findById(Long taskId) {
        for (Task task : TASK) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    public Task createTask(Task task) {

        TASK.add(task);
        return task;
    }

    public List<Task> findByUser(String username) {
        List<Task> tasksByUser = new ArrayList<>();
        for (Task task : TASK) {
            if (task.getUser().getUsername().equals(username)) {
                tasksByUser.add(task);
            }
        }
        return tasksByUser;
    }

    public Task updateTask(Task task) {
        var taskIndex = IntStream.range(0, TASK.size())
                .filter(index -> TASK.get(index).getId().equals(task.getId()))
                .findFirst()
                .orElse(-1);
        if (taskIndex > -1) {
            TASK.set(taskIndex, task);
            return task;
        }
        return null;
    }

    public void deleteTask(Long taskId) {
        var task = findById(taskId);
        if (task != null) {
            TASK.remove(task);
        }
    }

}
