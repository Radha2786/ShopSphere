package com.gl.ShopSphere.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoriesDTO {
    private Integer id;
    private String name;
    private List<ProductsDTO> productsDTOs;
}
