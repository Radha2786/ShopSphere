package com.gl.ShopSphere.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cart_itemsDTO {

    private Integer id;
    private CartDTO cartDTO;
    private ProductsDTO productsDTO;
    private Integer quantity;
}
