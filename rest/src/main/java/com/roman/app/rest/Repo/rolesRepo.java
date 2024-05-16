package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface rolesRepo extends JpaRepository<roles, Long> {
    Optional<roles> findByRoleId(long roleId);
    Optional<roles> findByRole(String role);
}
