package com.lupenmar.springtodo.repository;

import com.lupenmar.springtodo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    void deleteByUsername(String username);

    User findByUsername(String username);

    User findByEmail(String email);
}
