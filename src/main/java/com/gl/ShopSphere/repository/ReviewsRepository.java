package com.gl.ShopSphere.repository;

import com.gl.ShopSphere.entity.Products;
import com.gl.ShopSphere.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {

    public Reviews findByProduct(Products product);
}
