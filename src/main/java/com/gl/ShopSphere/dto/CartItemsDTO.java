package com.gl.ShopSphere.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemsDTO {

    private Integer id;
    private CartDTO cartDTO;
    private ProductsDTO productsDTO;
    private Integer quantity;
}
