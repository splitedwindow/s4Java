package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ChefRepo extends JpaRepository<Chef, Long> {
    Optional<Chef> findByEmailAndPassword(String email, String password);
}
