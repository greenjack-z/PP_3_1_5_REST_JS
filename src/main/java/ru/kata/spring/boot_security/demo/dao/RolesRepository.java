package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByAuthority(String authority);
}
