package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.Chef;
import com.roman.app.rest.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface customerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmailAndPassword(String email, String password);
}
