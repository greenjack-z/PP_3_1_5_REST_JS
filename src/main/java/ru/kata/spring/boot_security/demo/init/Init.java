package ru.kata.spring.boot_security.demo.init;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UsersService;

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
            user.setRoles(Set.of(rolesService.findRoleByName("ROLE_USER")));
            usersService.saveUser(user);
        }

        if (usersService.findByEmail("testadmin@kata.ru").isEmpty()) {
            User admin = new User();
            admin.setFirstname("Test admin");
            admin.setLastname("Test admin lastname");
            admin.setAge(100);
            admin.setEmail("testadmin@kata.ru");
            admin.setPassword("testadmin");
            admin.setRoles(Set.of(rolesService.findRoleByName("ROLE_ADMIN")));
            usersService.saveUser(admin);
        }
    }
}
