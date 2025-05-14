package com.gl.ShopSphere.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductsDTO {

    private Integer id;
    private String name;
    private String description;
    private Double price;
    private CategoriesDTO categoriesDTO;
    private List<ReviewsDTO> reviewsDTOs;
    private List<Cart_itemsDTO> cart_itemsDTOs;
    private List<Order_itemsDTO> order_itemsDTOs;
}
