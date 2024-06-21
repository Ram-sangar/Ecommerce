package com.ecommerce.demo.Carts;

import com.ecommerce.demo.Cart_Items.CartItem;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name="carts")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @Transient
    private Long userId;

    @Column
    private Double finalTotalPrice;

    @Column
    private Double finalGstAmount;

    @Column
    private Double finalQuantity;

    @Column
    private Double finalTotal;

    @Column
    private LocalDateTime cartTimestamp = LocalDateTime.now();


   @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;


}

