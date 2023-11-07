package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User findByUsername(String username);

    User findById(long id);

    void deleteById(long id);

    boolean updateUser(User user);

    boolean saveUser(User user);
}
