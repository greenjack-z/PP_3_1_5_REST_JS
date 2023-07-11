package ru.kata.spring.boot_security.demo.model;

import jakarta.persistence.Embeddable;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
public class Role implements GrantedAuthority {

    private String authority;

    public Role() {
        //constructor for Hibernate
    }

    public Role(String authority) {
        setAuthority(authority);
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        if (authority.toLowerCase().contains("admin")) {
            this.authority = "ROLE_ADMIN";
        } else {
            this.authority = "ROLE_USER";
        }
    }

    @Override
    public String toString() {
        return authority;
    }
}
