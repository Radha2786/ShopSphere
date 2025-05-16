package com.gl.ShopSphere.repository;

import com.gl.ShopSphere.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

    // get all products by category (for ex : in jeans category get all jeans)

    public Products findByName(String name);

    @Query("select p from products p JOIN FETCH p.reviews where p.id = :id")
    Optional<Products> findProductWithReviews(@Param("id") Integer id);

//    tells JPA:
//    Go to the Product entity, follow the reviews field, and join it using the foreign key defined in the Review entity.
//Hibernate (via JPA) will generate SQL something like:
//SELECT p.*, r.*
//    FROM product p
//    JOIN review r ON p.id = r.product_id
//    WHERE p.id = ?

//    With JOIN FETCH, you're saying:

//    Load the product and eagerly fetch the reviews too — in a single query.

}
