package ru.kata.spring.boot_security.demo.service;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UsersService {
    List<User> findAll();
    User findById(long id);
    User findUserByEmail(String email);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
