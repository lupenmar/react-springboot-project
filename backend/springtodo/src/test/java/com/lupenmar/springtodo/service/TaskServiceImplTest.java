package com.lupenmar.springtodo.service;

import com.lupenmar.springtodo.exception.NotFoundException;
import com.lupenmar.springtodo.exception.ValidationException;
import com.lupenmar.springtodo.model.Task;
import com.lupenmar.springtodo.repository.TaskRepository;
import com.lupenmar.springtodo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateTaskNotFound() {
        // Arrange
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task Title");
        task.setDescription("Task Description");

        when(taskRepository.findById(eq(1L))).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> taskService.updateTask(task));
    }

    @Test
    void testUpdateTaskInvalidTitle() {
        // Arrange
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Task Title");
        existingTask.setDescription("Task Description");

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Ab"); // Too short title

        when(taskRepository.findById(eq(1L))).thenReturn(Optional.of(existingTask));

        // Act & Assert
        assertThrows(ValidationException.class, () -> taskService.updateTask(task));
    }

    @Test
    void testUpdateTaskInvalidDescription() {
        // Arrange
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Task Title");
        existingTask.setDescription("Task Description");

        Task task = new Task();
        task.setId(1L);
        task.setDescription("Short"); // Too short description

        when(taskRepository.findById(eq(1L))).thenReturn(Optional.of(existingTask));

        assertThrows(ValidationException.class, () -> taskService.updateTask(task));
    }

}

