package com.gl.ShopSphere.service;

import com.gl.ShopSphere.Exception.ShoppingManagementSystemException;
import com.gl.ShopSphere.dto.*;
import com.gl.ShopSphere.entity.*;
import com.gl.ShopSphere.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private OrderItemsRepository order_itemsRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemsRepository cart_itemsRepository;

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
              List<CartItemsDTO> cartItemsDTOs = new ArrayList<>();
              for(CartItems item: product.getCart_items()){
                  CartItemsDTO itemsDTO = new CartItemsDTO();
                  itemsDTO.setId(item.getId());
                  itemsDTO.setQuantity(item.getQuantity());
                  cartItemsDTOs.add(itemsDTO);
              }
              productsDTO.setCart_itemsDTOS(cartItemsDTOs);
          }
          //order items mapping
          if(product.getOrder_items()!=null){
              List<OrderItemsDTO> order_itemsDTOS = new ArrayList<>();
              for(OrderItems item: product.getOrder_items()){
                  OrderItemsDTO itemDTO = new OrderItemsDTO();
                  itemDTO.setId(item.getId());
                  itemDTO.setQuantity(item.getQuantity());
                  itemDTO.setPrice(item.getPrice());
                  order_itemsDTOS.add(itemDTO);
              }
              productsDTO.setOrder_itemsDTOS(order_itemsDTOS);
          }
          productsDTOList.add(productsDTO);
      }
        return productsDTOList;
    }

    @Override
    public ResponseDTO updateOrderStatus(Integer orderId, String newStatus) throws ShoppingManagementSystemException {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new ShoppingManagementSystemException("Order not found"));
        order.setStatus(newStatus);
        ordersRepository.save(order);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Order status updated successfully");
        return responseDTO;
    }

    @Override
    public ResponseDTO addProduct(String role, ProductsDTO productsDTO) throws ShoppingManagementSystemException {
        if (!"admin".equalsIgnoreCase(role)) {
            throw new ShoppingManagementSystemException("Only admin can add products");
        }
        // check if category exists
        Integer categoryId = productsDTO.getCategoriesDTO().getId();
        Categories category = categoriesRepository.findById(categoryId).orElseThrow(()->new ShoppingManagementSystemException("category not found"));
        // create and populate product
        Products product = new Products();
        product.setName(productsDTO.getName());
        product.setPrice(productsDTO.getPrice());
        product.setDescription(productsDTO.getDescription());
        product.setCategories(category);

        //save product
        productsRepository.save(product);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Product added successfully with ID: " + product.getId());
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteProduct(Integer productId) throws ShoppingManagementSystemException {
        Products product = productsRepository.findById(productId).orElseThrow(()-> new ShoppingManagementSystemException("product with given id not found"));
        // delete associated reviews
        if(product.getReviews()!=null){
            reviewsRepository.deleteAll(product.getReviews());
        }
        // delete associated cart items
        if(product.getCart_items()!=null){
            cart_itemsRepository.deleteAll(product.getCart_items());
        }
        // delete associated order items
        if(product.getOrder_items()!=null){
            order_itemsRepository.deleteAll(product.getOrder_items());
        }
        //removing category association
        product.setCategories(null);
        // now deleting product
        productsRepository.delete(product);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Product and associated data deleted successfully");
        return responseDTO;
    }

    @Override
    public ProductsDTO getProductByName(String name) throws ShoppingManagementSystemException {
       Products product = productsRepository.findByName(name);
       if(product==null){
           throw new ShoppingManagementSystemException("product not found");
       }
       ProductsDTO productsDTO = new ProductsDTO();
       productsDTO.setName(product.getName());
       productsDTO.setPrice(product.getPrice());
       productsDTO.setId(product.getId());
       productsDTO.setDescription(product.getDescription());

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

        // Manually mapping category if present
        if(product.getCategories()!=null){
            CategoriesDTO categoriesDTO = new CategoriesDTO();
            categoriesDTO.setId(product.getCategories().getId());
            categoriesDTO.setName(product.getCategories().getName());
            productsDTO.setCategoriesDTO(categoriesDTO);
        }

        // Cart items mapping
        if(product.getCart_items()!=null){
            List<CartItemsDTO> cartItemsDTOs = new ArrayList<>();
            for(CartItems item: product.getCart_items()){
                CartItemsDTO itemsDTO = new CartItemsDTO();
                itemsDTO.setId(item.getId());
                itemsDTO.setQuantity(item.getQuantity());
                cartItemsDTOs.add(itemsDTO);
            }
            productsDTO.setCart_itemsDTOS(cartItemsDTOs);
        }

        if(product.getOrder_items()!=null){
            List<OrderItemsDTO> order_itemsDTOS = new ArrayList<>();
            for(OrderItems item: product.getOrder_items()){
                OrderItemsDTO itemDTO = new OrderItemsDTO();
                itemDTO.setId(item.getId());
                itemDTO.setQuantity(item.getQuantity());
                itemDTO.setPrice(item.getPrice());
                order_itemsDTOS.add(itemDTO);
            }
            productsDTO.setOrder_itemsDTOS(order_itemsDTOS);
        }

        return productsDTO;
    }

    @Override
    public ResponseDTO addtoCart(Integer userid, Integer productId, Integer quantity) throws ShoppingManagementSystemException {
//        Cart cart = new Cart();
        Users user = usersRepository.findById(userid).orElseThrow(()-> new ShoppingManagementSystemException("user not exist inside addtoCart service.."));
//        cart.setUser(user);
        Cart cart = user.getCart();
        CartItems cartItem = new CartItems();
        cartItem.setQuantity(quantity);
        Products product = productsRepository.findById(productId).orElseThrow(()-> new ShoppingManagementSystemException("product with given id doesn't exist inside add to cart service.."));
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cart_itemsRepository.save(cartItem);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Product added to cart");
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteFromCart(Integer cartId) throws ShoppingManagementSystemException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new ShoppingManagementSystemException("No cart found with the given ID in deleteFromCart method.."));
       if(cart.getCart_items()!=null && !cart.getCart_items().isEmpty()){
           cart_itemsRepository.deleteAll(cart.getCart_items());
       }
        cartRepository.delete(cart);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("cart along with cartitems deleted successfully");
        return responseDTO;
    }

    @Override
    public ResponseDTO placeOrder(Integer userId, Integer orderId) throws ShoppingManagementSystemException {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new ShoppingManagementSystemException("User not found"));
        Cart cart = user.getCart();

        if (cart.getCart_items().isEmpty()) {
            throw new ShoppingManagementSystemException("Cart is empty");
        }

        Orders order = new Orders();
        order.setUser(user);
        order.setStatus("Placed");
        List<OrderItems> orderItemsList = new ArrayList<>();
        Double total = Double.valueOf(0);
        for(CartItems cartItem: cart.getCart_items()){
            OrderItems orderItem = new OrderItems();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setOrder(order);
            Double price = cartItem.getProduct().getPrice();
            total = total+price;
            orderItemsList.add(orderItem);
        }

        order.setCreated_at(LocalDateTime.now());
        order.setTotal(total);
        order.setOrder_itemsList(orderItemsList);
//        order.setOrder_itemsList(cart.getCart_items());
        order = ordersRepository.save(order);

        cart.getCart_items().clear();
        cartRepository.save(cart);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Order placed successfully with ID: " + order.getId());
        return responseDTO;
    }

//    @Override
//    public ProductsDTO getProductWithReviews(Integer productid) throws ShoppingManagementSystemException {
//        return null;
//    }

    //    @Override
//    public UsersDTO getAllCustomers(String role) throws ShoppingManagementSystemException {
//        return null;
//    }

    //    @Override
//    public Cart_itemsDTO updateCartQuantity(Integer cartItemId, int newQuantity) {
//        return null;
//    }

    //    @Override
//    public ResponseDTO postAReview(Integer userid, Integer productid, ReviewsDTO reviewsDTO) throws ShoppingManagementSystemException {
//        return null;
//    }
}










