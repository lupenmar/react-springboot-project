package com.lupenmar.springtodo.service;

import com.lupenmar.springtodo.model.User;
import com.lupenmar.springtodo.repository.InMemoryUserDAO;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryUserServiceImpl implements UserService {

    private final InMemoryUserDAO repository;

    @Override
    public List<User> findAllUser() {
        return repository.findAllUser();
    }

    @Override
    public User createUser(User user) {
        return repository.createUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User updateUser(User user) {
        return repository.updateUser(user);
    }

    @Override
    public void deleteUser(String username) {
        repository.deleteUser(username);
    }

}
