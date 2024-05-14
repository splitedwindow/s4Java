package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.Dishes;
import com.roman.app.rest.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ordersRepo extends JpaRepository<Orders, Long> {
    Optional<Orders> findByOrderId(long orderId);
}
