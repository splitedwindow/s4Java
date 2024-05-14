package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.Chef;
import com.roman.app.rest.Models.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface dishesRepo extends JpaRepository<Dishes, Long> {
    Optional<Dishes> findByDishId(long dish_id);
}
