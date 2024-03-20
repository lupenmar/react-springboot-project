package com.lupenmar.springtodo.service;

import com.lupenmar.springtodo.exception.NotFoundException;
import com.lupenmar.springtodo.model.User;
import com.lupenmar.springtodo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllUser() {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(new User("user1", "user1@example.com", "password"));
        userList.add(new User("user2", "user2@example.com", "password"));

        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> result = userService.findAllUser();

        // Assert
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser() {
        // Arrange
        User newUser = new User();
        newUser.setUsername("newuser");
        newUser.setEmail("newuser@example.com");
        newUser.setPassword("password");

        when(userRepository.save(newUser)).thenReturn(newUser);

        // Act
        User result = userService.createUser(newUser);

        // Assert
        assertEquals(newUser, result);
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void testFindByUsername() {
        // Arrange
        String username = "existinguser";
        User existingUser = new User();
        existingUser.setUsername(username);
        existingUser.setEmail("existinguser@example.com");
        existingUser.setPassword("password");

        when(userRepository.findByUsername(username)).thenReturn(existingUser);

        // Act
        User result = userService.findByUsername(username);

        // Assert
        assertEquals(existingUser, result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testUpdateUser() {
        // Arrange
        User existingUser = new User();
        existingUser.setUsername("existinguser");
        existingUser.setEmail("existinguser@example.com");
        existingUser.setPassword("password");
        User updatedUser = new User();
        updatedUser.setUsername("existinguser");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setPassword("password");

        when(userRepository.findByUsername(existingUser.getUsername())).thenReturn(existingUser);
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Act
        User result = userService.updateUser(updatedUser);

        // Assert
        assertEquals(updatedUser.getEmail(), result.getEmail());
        verify(userRepository, times(1)).findByUsername(existingUser.getUsername());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void testUpdateUserNotFound() {
        // Arrange
        User updatedUser = new User();
        updatedUser.setUsername("nonexistinguser");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setPassword("newpassword");

        when(userRepository.findByUsername(updatedUser.getUsername())).thenReturn(null);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userService.updateUser(updatedUser));
        verify(userRepository, times(1)).findByUsername(updatedUser.getUsername());
        verify(userRepository, never()).save(any());
    }

    @Test
    void testDeleteUser() {
        // Arrange
        String username = "existinguser";

        // Act
        userService.deleteUser(username);

        // Assert
        verify(userRepository, times(1)).deleteByUsername(username);
    }
}
