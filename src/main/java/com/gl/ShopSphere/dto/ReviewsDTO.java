package com.gl.ShopSphere.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReviewsDTO {

    private Integer id;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
    private ProductsDTO productsDTO;
    private UsersDTO usersDTO;
}
