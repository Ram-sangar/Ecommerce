package com.ecommerce.demo.Cart_Items;

import com.ecommerce.demo.Carts.CartRepository;

import com.ecommerce.demo.Category.Category;
import com.ecommerce.demo.Product.Product;
import com.ecommerce.demo.Product.ProductRepository;
import com.ecommerce.demo.Users.User;
import com.ecommerce.demo.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    public CartItem createcartItem(CartItem cartItem) {
        User user = userRepository.findById(cartItem.getUserId())
                .orElseThrow(()->new ExpressionException("User does not exist"));
        cartItem.setUser(user);

        Product product = productRepository.findById(cartItem.getProductId()).orElseThrow(()->new ExpressionException("Product does not exist"));
        cartItem.setProduct((List<Product>) product);

        cartItem.setUnitPrice(cartItem.getProduct().getFirst().getProductPrice());
        cartItem.setGstAmount(cartItem.getProduct().getFirst().getGstPercentage());
        cartItem.setQuantity(cartItem.getQuantity());
        cartItem.setItemTotal(cartItem.getUnitPrice()*cartItem.getGstAmount()* cartItem.getQuantity());
        return cartItemRepository.save(cartItem);
    }


    //getall
    public List<CartItem> listcartitem() {
        return cartItemRepository.findAll();
    }

    //getbyid
    public Optional<CartItem> findById(long cartItemId) {
        return cartItemRepository.findById(cartItemId);
    }

    //update
    public void editcategory( long cartItemId, CartItem updatecartItem){

        CartItem cartItem = cartItemRepository.getById(cartItemId);

        if (updatecartItem.getQuantity() != null) {
            cartItem.setQuantity(updatecartItem.getQuantity());
            cartItem.setItemTotal(cartItem.getUnitPrice()*cartItem.getGstAmount()* cartItem.getQuantity());
            cartItem.setCartItemTimestamp(LocalDateTime.now());
        }
        cartItemRepository.save(cartItem);
    }

    //delete
    public void deleteByid(long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    //getid
    public boolean findId(long cartItemId) {
        return cartItemRepository.findById(cartItemId).isPresent();
    }


}
