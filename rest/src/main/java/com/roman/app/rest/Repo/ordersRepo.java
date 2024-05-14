package com.roman.app.rest.Repo;

import com.roman.app.rest.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ordersRepo extends JpaRepository<Orders, Long> {
}
