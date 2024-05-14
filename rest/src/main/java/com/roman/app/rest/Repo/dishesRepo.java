package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface dishesRepo extends JpaRepository<Dishes, Long> {
}
