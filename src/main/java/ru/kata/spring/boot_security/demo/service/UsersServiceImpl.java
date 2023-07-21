package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements ru.kata.spring.boot_security.demo.service.UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return usersRepository.findById(id).orElseThrow();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usersRepository.findUserByEmail(email);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        usersRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (!user.getPassword().startsWith("{bcrypt}")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        usersRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        usersRepository.delete(user);
    }

}
