package com.gl.ShopSphere.repository;

import com.gl.ShopSphere.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {

}
