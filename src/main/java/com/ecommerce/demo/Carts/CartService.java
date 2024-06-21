
package com.ecommerce.demo.Carts;
import com.ecommerce.demo.Dto.CartDTO;
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
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public void createcart(CartDTO cartDTO) {
        /*System.out.println("service"+ cart.getUser());
        User user = userRepository.getById(cart.getUserId());
        cartRepository.save(cart); */

        Long userId = cartDTO.getCart().getTempUserId();

        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User does not exists"));

        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart());

        cart.setUser(user);

        cart = cartRepository.save(cart);

        Product product = productRepository.findById(cartDTO.getCartItem().getTempProductId()).orElseThrow(() -> new Exception("Product does not exists"));

        //CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId()).orElse(new CartItem());

        int quantity = cartDTO.getCartItem().getQuantity();
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setProductPrice(product.getPrice());
        calculateTotal(cartItem, cart, product);

        cartRepository.save(cart);


    }

    public List<Cart> findall() {
        return cartRepository.findAll();
    }

    public Optional<Cart> findcartById(long cardid) {
        return cartRepository.findById(cardid);
    }

    public void updatecart(long cartid, Cart Updatecart) {
        Cart cart = cartRepository.getById(cartid);
        if(cart.getFinalGstAmount()!=null){
            cart.setFinalTotal(cart.getFinalTotal());
            cart.setCartTimestamp(LocalDateTime.now());
        }
        if(cart.getFinalQuantity()!=null){
            cart.setFinalQuantity(cart.getFinalQuantity());
            cart.setCartTimestamp(LocalDateTime.now());
        }
        if(cart.getFinalTotalPrice()!=null){
            cart.setFinalGstAmount(cart.getFinalGstAmount());
            cart.setCartTimestamp(LocalDateTime.now());
        }
        cartRepository.save(cart);
    }

    public void deleteid(long cardId) {
        cartRepository.deleteById(cardId);
    }

    public boolean findId(long cartId) {
        return cartRepository.findById(cartId).isPresent();
    }

}

