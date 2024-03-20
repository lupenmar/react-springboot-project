package com.lupenmar.springtodo.service;


import com.lupenmar.springtodo.exception.NotFoundException;

import com.lupenmar.springtodo.model.User;
import com.lupenmar.springtodo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;


import org.springframework.stereotype.Service;


import java.util.List;


@Service
@AllArgsConstructor
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<User> findAllUser() {
        return repository.findAll();
    }


    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User updateUser(User user) {

        User existingUser = repository.findByUsername(user.getUsername());

        if (existingUser == null) {
            throw new NotFoundException("User not found");
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        return repository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        repository.deleteByUsername(username);
    }

}
