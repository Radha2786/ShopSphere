package com.gl.ShopSphere.repository;

import com.gl.ShopSphere.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
