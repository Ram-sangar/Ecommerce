
package com.ecommerce.demo.OrderItem;

import com.ecommerce.demo.Cart_Items.CartItem;
import com.ecommerce.demo.Order.Order;
import com.ecommerce.demo.Product.Product;
import com.ecommerce.demo.Users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderItems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderitemId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id" ,referencedColumnName = "orderId")
    private Order order;

    @Transient
    private long orderId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_item_id" ,referencedColumnName = "cartItemId")
    private CartItem cartItem;

    @Transient
    private long cartItemId;

    @Transient
    private long userId;
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDateTime orderItemTimestamp = LocalDateTime.now();


}

