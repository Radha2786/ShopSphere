package com.gl.ShopSphere.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class Order_items {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

//    “This item belongs to one specific order, identified by order_id.”
//    May items can belong to 1 specific order
//     orders table:
//    id	user_id	total	status	created_at
//      1	     5	2000	pending	 2024-05-14
//
//    order_items table:
//    id	order_id	product_id	quantity	price
//    1	    1	              10	      1	        1000
//    2     1	              11	      2         500

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
}
