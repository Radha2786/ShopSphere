package com.gl.ShopSphere.controller;

import com.gl.ShopSphere.Exception.ShoppingManagementSystemException;
import com.gl.ShopSphere.dto.ProductsDTO;
import com.gl.ShopSphere.dto.ResponseDTO;
import com.gl.ShopSphere.dto.UsersDTO;
import com.gl.ShopSphere.service.ShoppingManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SMS")
public class ShoppingManagementController {

    @Autowired
    private ShoppingManagementService shoppingManagementService;

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody @Valid UsersDTO usersDTO) throws ShoppingManagementSystemException{
        System.out.println("Inside register..........");
        ResponseDTO responseDTO = shoppingManagementService.register(usersDTO);
//        return new ResponseEntity<>(body, statusCode)
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "get-all-products")
    public ResponseEntity<List<ProductsDTO>> getAllProducts() throws ShoppingManagementSystemException{
        List<ProductsDTO> productsDTOList = shoppingManagementService.getAllProducts();
        return new ResponseEntity<>(productsDTOList,HttpStatus.OK);
    }

    @PutMapping(value = "/update-order-status")
    public ResponseEntity<ResponseDTO> updateOrderStatus(@PathVariable Integer orderId, @PathVariable String newStatus) throws ShoppingManagementSystemException{
        ResponseDTO responseDTO = shoppingManagementService.updateOrderStatus(orderId, newStatus);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/add-product")
    public ResponseEntity<ResponseDTO> addProduct(@PathVariable String role,@RequestBody @Valid ProductsDTO productsDTO) throws ShoppingManagementSystemException{
        ResponseDTO responseDTO = shoppingManagementService.addProduct(role,productsDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "delete-product")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Integer productId) throws ShoppingManagementSystemException{
        ResponseDTO responseDTO = shoppingManagementService.deleteProduct(productId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping(value = "get-product-by-name")
    public ResponseEntity<ProductsDTO> getProductByName(@PathVariable String name) throws ShoppingManagementSystemException{
        ProductsDTO productsDTO = shoppingManagementService.getProductByName(name);
        return new ResponseEntity<>(productsDTO,HttpStatus.OK);
    }

    @PostMapping(value = "add-to-cart")
    public ResponseEntity<ResponseDTO> addtoCart(@PathVariable Integer userid, @PathVariable Integer productId, @PathVariable Integer quantity) throws ShoppingManagementSystemException{
        ResponseDTO responseDTO = shoppingManagementService.addtoCart(userid, productId, quantity);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

    }
    @DeleteMapping(value = "delete-from-cart")
    public ResponseEntity<ResponseDTO> deleteFromCart(@PathVariable Integer cartId) throws ShoppingManagementSystemException{
        ResponseDTO responseDTO = shoppingManagementService.deleteFromCart(cartId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "place-order")
    public ResponseEntity<ResponseDTO> placeOrder(@PathVariable Integer userId, @PathVariable Integer orderId) throws ShoppingManagementSystemException{
        ResponseDTO responseDTO = shoppingManagementService.placeOrder(userId,orderId);
        return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
    }

}
