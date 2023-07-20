package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    @EntityGraph(value = "UserWithRoles", type = EntityGraph.EntityGraphType.FETCH)
    Optional<User> findUserByEmail(String username);

}
