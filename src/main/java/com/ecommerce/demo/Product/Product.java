package com.ecommerce.demo.Product;

import com.ecommerce.demo.Cart_Items.CartItem;
import com.ecommerce.demo.Category.Category;
import com.ecommerce.demo.Users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" ,referencedColumnName = "userId")
    private User user;

    @Transient
    private Long tempUserId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;

    @Transient
    private Long categoryId;

    @Column
    private String productName;

    @Column
    private String productDescription;

    @Column
    private Double productPrice;

    @Column
    private Double gstPercentage;

    @Column(nullable = false)
    private Timestamp productTimestamp;

    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CartItem> cartItems;


}
