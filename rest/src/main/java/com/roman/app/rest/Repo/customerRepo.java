package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface customerRepo extends JpaRepository<customer, Long> {
    Optional<customer> findByEmail(String email); // Add this method
    Optional<customer> findByEmailAndPassword(String email, String password);
}
