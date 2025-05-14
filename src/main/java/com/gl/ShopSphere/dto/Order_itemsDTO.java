package com.gl.ShopSphere.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order_itemsDTO {
    private Integer id;
    private Integer quantity;
    private Double price;
    private OrdersDTO ordersDTO;
    private ProductsDTO productsDTO;
}
