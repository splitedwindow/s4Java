package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.сhef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChefRepo extends JpaRepository<сhef, Long> {
    Optional<сhef> findByEmailAndPassword(String email, String password);
}
