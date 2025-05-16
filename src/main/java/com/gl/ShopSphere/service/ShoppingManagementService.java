package com.gl.ShopSphere.service;

import com.gl.ShopSphere.Exception.ShoppingManagementSystemException;
import com.gl.ShopSphere.dto.*;

import java.util.List;

public interface ShoppingManagementService {

    public ResponseDTO register(UsersDTO usersDTO) throws ShoppingManagementSystemException;  // e.g. "Admin" or "client"

    public List<ProductsDTO> getAllProducts() throws ShoppingManagementSystemException;

    public ResponseDTO updateOrderStatus(Integer orderId, String newStatus) throws ShoppingManagementSystemException;

    public ResponseDTO addProduct(String role, ProductsDTO productsDTO) throws ShoppingManagementSystemException;

    public ResponseDTO deleteProduct(Integer productId) throws ShoppingManagementSystemException;

//    public UsersDTO getAllCustomers(String role) throws ShoppingManagementSystemException;

    public ProductsDTO getProductByName(String name) throws ShoppingManagementSystemException;

//    public ResponseDTO postAReview( Integer userid,Integer productid, ReviewsDTO reviewsDTO) throws ShoppingManagementSystemException;

    public ResponseDTO addtoCart( Integer userid, Integer productId, Integer quantity) throws ShoppingManagementSystemException;

//    public ResponseDTO deleteFromCart(CartDTO cartDTO, UsersDTO usersDTO) throws ShoppingManagementSystemException;

    public ResponseDTO deleteFromCart(Integer cartItemId) throws ShoppingManagementSystemException; // it will delete from the cart items

    // 10.1 Update Cart Quantity
//    public Cart_itemsDTO updateCartQuantity(Integer cartItemId, int newQuantity);

    public ResponseDTO placeOrder(Integer userid, Integer orderid) throws ShoppingManagementSystemException;

//    public ProductsDTO getProductWithReviews(Integer productid) throws ShoppingManagementSystemException;

    // 13. Create/Manage Categories
//    Categories addOrUpdateCategory(Categories category);
//    void deleteCategory(Integer categoryId);
}
