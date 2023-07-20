package ru.kata.spring.boot_security.demo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UsersRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    private final RolesService rolesService;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, RolesService rolesService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolesService = rolesService;
    }

    @PostConstruct
    private void initDB() {

        Role roleUser = rolesService.findRoleByName("ROLE_USER");
        User user = new User();
        user.setFirstname("Test user");
        user.setLastname("Test user lastname");
        user.setAge(100);
        user.setEmail("testuser@kata.ru");
        user.setPassword(passwordEncoder.encode("testuser"));
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        user.setRoles(roles);
        user.setEnabled(true);
        System.err.println(user);
        usersRepository.save(user);

//        Role roleAdmin = rolesService.findRoleByName("ROLE_ADMIN");
//        User admin = new User();
//        admin.setFirstname("Test admin");
//        admin.setLastname("Test admin lastname");
//        admin.setAge(100);
//        admin.setEmail("testadmin@kata.ru");
//        admin.setPassword(passwordEncoder.encode("testadmin"));
//        admin.setRoles(Set.of(roleAdmin));
//        admin.setEnabled(true);
//        usersRepository.save(admin);
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
