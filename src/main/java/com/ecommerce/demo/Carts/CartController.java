package com.ecommerce.demo.Carts;

import com.ecommerce.demo.ApiResponse;
import com.ecommerce.demo.Dto.CartDTO;
import com.ecommerce.demo.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> Listcart (@RequestBody CartDTO cartDTO){
        if(! cartService.findId(cart.getUserId())) {
            return new ResponseEntity<>(new ApiResponse(false,"user id does not exist"),HttpStatus.NOT_FOUND);}
       /* if(!cartService.findId(cart.getCartItems())){
            return new ResponseEntity<>(new ApiResponse(false,"user id does not exist"),HttpStatus.NOT_FOUND);}
        }*/
        cartService.createcart(cart);
        return new ResponseEntity<>(new ApiResponse(true,"cart was created successfully"), HttpStatus.CREATED);

        /*try {
            cartService.addToCart(cartDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "User does not exist", e);
        }

        return "success";*/
    }
    public String addToCart(@RequestBody CartDTO cartDTO) throws Exception {
        try {
            cartService.addToCart(cartDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "User does not exist", e);
        }

        return "success";
    }

    public ResponseEntity<List<Cart>> getAllAddresss(){
        List<Cart> cartsList = cartService.findall();
        return ResponseEntity.ok(cartsList);
    }

    @GetMapping("/List/{cartId}")
    public Optional<Cart> findcartbyid (@PathVariable("cardId") long cardid){
        return cartService.findcartById(cardid);
    }

    @PutMapping("/update/{cartId}")
    public ResponseEntity<ApiResponse> editcart(@PathVariable("cartId") long cartid,@RequestBody Cart cart){
        cartService.updatecart(cartid,cart);
        return new ResponseEntity<>(new ApiResponse(true,"cart update successfully"),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{CardId}")
    public ResponseEntity<String> delete(@PathVariable("cardId") long cardId){
        cartService.deleteid(cardId);
        return ResponseEntity.ok("delete success");
    }


}
