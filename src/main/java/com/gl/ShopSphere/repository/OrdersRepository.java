package com.gl.ShopSphere.repository;

import com.gl.ShopSphere.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    // create an order
}
