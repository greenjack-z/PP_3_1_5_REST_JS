package ru.kata.spring.boot_security.demo.init;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.util.HashSet;
import java.util.Set;

@Component
public class Init {

    private final UsersService usersService;
    private final RolesService rolesService;

    public Init(UsersService usersService, RolesService rolesService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
    }

    @PostConstruct
    private void initDB() {
        if (usersService.findByEmail("testuser@kata.ru").isEmpty()) {
            User user = new User();
            user.setFirstname("Test user");
            user.setLastname("Test user lastname");
            user.setAge(100);
            user.setEmail("testuser@kata.ru");
            user.setPassword("testuser");
            user.setRoles(Set.of(new Role("ROLE_USER")));
            usersService.saveUser(user);
        }

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
}
