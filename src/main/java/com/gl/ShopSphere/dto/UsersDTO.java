package com.gl.ShopSphere.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsersDTO {

    private Integer id;
    private String name;
    private String email;
    private String role;
    private String password;
    private List<OrdersDTO> ordersDTOs;
    private CartDTO cartDTO;
    private List<ReviewsDTO> reviewsDTOs;
}
