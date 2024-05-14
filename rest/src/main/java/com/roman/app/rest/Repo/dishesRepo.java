package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.dishes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface dishesRepo extends JpaRepository<dishes, Long> {
    Optional<dishes> findByDishId(long dish_id);
}
