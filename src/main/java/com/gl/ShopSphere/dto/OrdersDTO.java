package com.gl.ShopSphere.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrdersDTO {

    private Integer id;
    private Double total;
    private String status;
    private LocalDateTime created_at;
    private UsersDTO usersDTO;
    private List<OrderItemsDTO> order_itemsDTOS;

}
