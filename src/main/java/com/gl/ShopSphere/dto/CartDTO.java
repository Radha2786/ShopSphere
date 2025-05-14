package com.gl.ShopSphere.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO {
    private Integer id;
    private UsersDTO usersDTO;
    private List<Cart_itemsDTO> cart_itemsDTOs;
}
