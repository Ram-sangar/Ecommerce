package com.ecommerce.demo.Cart_Items;

//import com.ecommerce.demo.Carts.Cart;
import com.ecommerce.demo.Product.Product;
import com.ecommerce.demo.Users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cartItems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    /*@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "Id")
    private Cart cart;

    @Transient
    private long cartId;*/
    private long cartId = 00000;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" ,referencedColumnName = "userId")
    private User user;

    @Transient
    private long userId;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    /*@JoinTable(
            name="product_id",
            joinColumns = @JoinColumn(name = "cart_item_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )*/
    private List<Product> product;

    @Column(nullable = false)
    private long productId;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double gstAmount;

    @Column(nullable = false)
    private Double itemTotal;

    @Column(nullable = false)
    private LocalDateTime cartItemTimestamp = LocalDateTime.now();

}
