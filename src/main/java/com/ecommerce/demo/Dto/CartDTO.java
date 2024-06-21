package com.ecommerce.demo.Dto;

import com.ecommerce.demo.Cart_Items.CartItem;
import com.ecommerce.demo.Carts.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    Cart cart;

    CartItem cartItem;
}

