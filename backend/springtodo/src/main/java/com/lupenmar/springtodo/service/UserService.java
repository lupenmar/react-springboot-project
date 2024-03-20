package com.lupenmar.springtodo.service;


import com.lupenmar.springtodo.model.User;


import java.util.List;

public interface UserService {
    List<User> findAllUser();

    User createUser(User user);

    User findByUsername(String username);

    User updateUser(User user);

    void deleteUser(String username);

}
