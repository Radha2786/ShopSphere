package com.gl.ShopSphere.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "products dto should not be null")
    @Valid
    private List<ProductsDTO> productsDTOs;
}
