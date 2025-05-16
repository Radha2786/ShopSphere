package com.gl.ShopSphere.service;

import com.gl.ShopSphere.Exception.ShoppingManagementSystemException;
import com.gl.ShopSphere.dto.*;
import com.gl.ShopSphere.entity.*;
import com.gl.ShopSphere.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShoppingManagementServiceImpl implements ShoppingManagementService{

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private Order_itemsRepository order_itemsRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private Cart_itemsRepository cart_itemsRepository;

    @Override
    public ResponseDTO register(UsersDTO usersDTO) throws ShoppingManagementSystemException {
        Users user= usersRepository.findByEmail(usersDTO.getEmail());
        if(user!=null){
            throw new ShoppingManagementSystemException("user already exists");
        }
        user = new Users();
        user.setName(usersDTO.getName());
        user.setEmail(usersDTO.getEmail());
        user.setPassword(usersDTO.getPassword());
        user.setRole(usersDTO.getRole());
//        user.setCart(usersDTO.getCartDTO());
//        user.setOrders();
//        user.setReviews();
        Cart cart = new Cart();
        cart.setUser(user); // set bi-directional mapping
        user.setCart(cart);
        user = usersRepository.save(user); // will save both user and cart bcz of cascade
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("user added successfully with user's id: "+ user.getId() + " and cart id: "+ user.getCart().getId());
        return responseDTO;
    }

    @Override
    public List<ProductsDTO> getAllProducts() throws ShoppingManagementSystemException {
      List<Products> productsList = productsRepository.findAll();
      if(productsList.isEmpty()){
          throw new ShoppingManagementSystemException("no products..");
      }
      List<ProductsDTO> productsDTOList = new ArrayList<>();
      for(Products product: productsList){
         ProductsDTO productsDTO = new ProductsDTO();
         productsDTO.setId(product.getId());
         productsDTO.setName(product.getName());
         productsDTO.setDescription(product.getDescription());
         productsDTO.setPrice(product.getPrice());

          // Manually mapping category if present
         if(product.getCategories()!=null){
             CategoriesDTO categoriesDTO = new CategoriesDTO();
             categoriesDTO.setId(product.getCategories().getId());
             categoriesDTO.setName(product.getCategories().getName());
             productsDTO.setCategoriesDTO(categoriesDTO);
         }

          // Map reviews
         if(product.getReviews()!=null){
             List<ReviewsDTO> reviewsDTOList = new ArrayList<>();
             for(Reviews review: product.getReviews()){
                 ReviewsDTO reviewsDTO = new ReviewsDTO();
                 reviewsDTO.setId(review.getId());
                 reviewsDTO.setRating(review.getRating());
                 reviewsDTO.setComment(review.getComment());
                 reviewsDTO.setCreatedAt(review.getCreatedAt());
                 reviewsDTOList.add(reviewsDTO);
             }
             productsDTO.setReviewsDTOs(reviewsDTOList);
         }
          // Cart items mapping
          if(product.getCart_items()!=null){
              List<Cart_itemsDTO> cartItemsDTOs = new ArrayList<>();
              for(Cart_items item: product.getCart_items()){
                  Cart_itemsDTO itemsDTO = new Cart_itemsDTO();
                  itemsDTO.setId(item.getId());
                  itemsDTO.setQuantity(item.getQuantity());
                  cartItemsDTOs.add(itemsDTO);
              }
              productsDTO.setCart_itemsDTOs(cartItemsDTOs);
          }
          //order items mapping
          if(product.getOrder_items()!=null){
              List<Order_itemsDTO> order_itemsDTOS = new ArrayList<>();
              for(Order_items item: product.getOrder_items()){
                  Order_itemsDTO itemDTO = new Order_itemsDTO();
                  itemDTO.setId(item.getId());
                  itemDTO.setQuantity(item.getQuantity());
                  itemDTO.setPrice(item.getPrice());
                  order_itemsDTOS.add(itemDTO);
              }
              productsDTO.setOrder_itemsDTOs(order_itemsDTOS);
          }
          productsDTOList.add(productsDTO);
      }
        return productsDTOList;
    }

    @Override
    public ResponseDTO updateOrderStatus(Integer orderId, String newStatus) throws ShoppingManagementSystemException {
        return null;
    }

    @Override
    public ResponseDTO addProduct(String role, ProductsDTO productsDTO) throws ShoppingManagementSystemException {
        return null;
    }

    @Override
    public ResponseDTO deleteProduct(Integer productId) throws ShoppingManagementSystemException {
        return null;
    }

    @Override
    public UsersDTO getAllCustomers(String role) throws ShoppingManagementSystemException {
        return null;
    }

    @Override
    public ProductsDTO getProductByName(String name) throws ShoppingManagementSystemException {
        return null;
    }

    @Override
    public ResponseDTO postAReview(Integer userid, Integer productid, ReviewsDTO reviewsDTO) throws ShoppingManagementSystemException {
        return null;
    }

    @Override
    public ResponseDTO addtoCart(Integer userid, Integer productId, Integer quantity) throws ShoppingManagementSystemException {
        return null;
    }

    @Override
    public ResponseDTO deleteFromCart(Integer cartItemId) throws ShoppingManagementSystemException {
        return null;
    }

    @Override
    public Cart_itemsDTO updateCartQuantity(Integer cartItemId, int newQuantity) {
        return null;
    }

    @Override
    public ResponseDTO placeOrder(Integer userid, Integer orderid) throws ShoppingManagementSystemException {
        return null;
    }

    @Override
    public ProductsDTO getProductWithReviews(Integer productid) throws ShoppingManagementSystemException {
        return null;
    }
}










