package com.gl.ShopSphere.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UsersDTO {

    private Integer id;

    @Pattern(regexp = "[A-Z][a-z]+\\s[A-Z][a-z]+", message = "name should be of exactly 2 words")
    private String name;

    @Email(message = "Email should be of email pattern")
    private String email;

    @NotBlank(message = "comment should not be blank")
    private String role;

    @NotNull(message = "password should not be null")
    private String password;

    private List<OrdersDTO> ordersDTOs;


    private CartDTO cartDTO;

    @NotEmpty(message = "book dto can not be empty")
    @Valid  //( for providing the validation in ReviewsDTO as well otherwise validation won't work in that class )
    private List<ReviewsDTO> reviewsDTOs;
}
