package com.gl.ShopSphere.repository;

import com.gl.ShopSphere.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
}
