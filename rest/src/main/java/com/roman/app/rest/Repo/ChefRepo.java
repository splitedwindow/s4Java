package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.chef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChefRepo extends JpaRepository<chef, Long> {
    Optional<chef> findByEmail(String email); // Add this method
    Optional<chef> findByEmailAndPassword(String email, String password);
}
