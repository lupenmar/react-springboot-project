package com.lupenmar.springtodo.repository;

import com.lupenmar.springtodo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    void deleteById(Long id);

    List<Task> findByUserUsername(String user);

    Optional<Task> findById(Long taskId);

}
