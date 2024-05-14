package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepo extends JpaRepository<Customer, Long> {
}
