package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService extends UserDetailsService {
   // boolean saveUser(User user);

    List<User> getAllUsers();

    User findByUsername(String username);

    User findById(long id);

    void deleteById(long id);

    List<Role> getAllRoles();

    User updateUser(User user);

    boolean saveUser(User user);
}
