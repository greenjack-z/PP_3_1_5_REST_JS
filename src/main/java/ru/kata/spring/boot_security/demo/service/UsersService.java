package ru.kata.spring.boot_security.demo.service;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<User> findAll();
    User findById(long id);
    Optional<User> findByEmail(String email);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
