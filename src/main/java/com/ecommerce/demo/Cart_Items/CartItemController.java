
package com.ecommerce.demo.Cart_Items;

import com.ecommerce.demo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createcartItem(@RequestBody CartItem cartItem) {
        if (!cartItemService.findId(cartItem.getUserId())) {
            return new ResponseEntity<>(new ApiResponse(false, "user id does not exist"), HttpStatus.NOT_FOUND);
        }
        cartItemService.createcartItem(cartItem);
        return new ResponseEntity<>(new ApiResponse(true, "create a new  cartItem"), HttpStatus.CREATED);
    }

    @GetMapping("/List")
    public ResponseEntity<List<CartItem>> listcartItem() {
         List<CartItem> CartItemList = cartItemService.listcartitem();
         return ResponseEntity.ok(CartItemList);
    }

    @GetMapping("/List/{cartItemId}")
    public Optional<CartItem> findById(@PathVariable("cartItemId") long cartItemId) {
        return cartItemService.findById(cartItemId);
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable("cartItemId") long cartItemId, @RequestBody CartItem cartItem) {

        //check id
        if (!cartItemService.findId(cartItemId)) {
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
        }

        // Validate that at least one field is not null
        if (cartItem.getQuantity() == null) {
            return new ResponseEntity<>(new ApiResponse(false, "At least one field must be provided."), HttpStatus.NOT_FOUND);
        }

        //save
        cartItemService.editcategory(cartItemId, cartItem);

        return new ResponseEntity<>(new ApiResponse(true, "cart Item update successfully"), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<String> deleteById(@PathVariable("cartItemId") long cartItemId) {
        System.out.println("delete by cartItem " + cartItemId);
        cartItemService.deleteByid(cartItemId);
        return ResponseEntity.ok("delete success");
    }

}

