package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ordersRepo extends JpaRepository<orders, Long> {
    Optional<orders> findByOrderId(long orderId);
}
