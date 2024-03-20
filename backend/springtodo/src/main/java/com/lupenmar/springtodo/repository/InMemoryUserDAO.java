package com.lupenmar.springtodo.repository;

import com.lupenmar.springtodo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

@Repository
public class InMemoryUserDAO {

    private final List<User> USERS = new ArrayList<>();

    public List<User> findAllUser() {
        return USERS;
    }

    public User createUser(User user) {

        USERS.add(user);
        return user;
    }

    public User findByUsername(String username) {

        return USERS.stream()
                .filter(element -> element.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public User updateUser(User user) {
        var userIndex = IntStream.range(0, USERS.size())
                .filter(index -> USERS.get(index).getUsername().equals(user.getUsername()))
                .findFirst()
                .orElse(-1);
        if (userIndex > -1) {
            USERS.set(userIndex, user);
            return user;
        }
        return null;
    }

    public void deleteUser(String username) {
        var user = findByUsername(username);
        if (user != null) {
            USERS.remove(user);
        }
    }
}
