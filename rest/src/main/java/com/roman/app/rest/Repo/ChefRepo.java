package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepo extends JpaRepository<Chef, Long> {
}
