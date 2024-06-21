package com.ecommerce.demo.Users;


import com.ecommerce.demo.Address.Address;
import com.ecommerce.demo.Carts.Cart;
import com.ecommerce.demo.Category.Category;
import com.ecommerce.demo.Order.Order;
import com.ecommerce.demo.Payment.Payment;
import com.ecommerce.demo.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

    @Entity
    @Data
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="userId")
        private Long userId;

        @Column(name="email",nullable = false, unique = true)
        private String email;

        @Column(name="firstName",nullable = false)
        private String firstName;

        @Column(name="lastName",nullable = false)
        private String lastName;

        @Column(name="password",nullable = false)
        private String password;

        @Column(name="type",nullable = false)
        private String type;  // BUYER or SELLER

        @Column(name="mobileNumber",nullable = false)
        private String mobileNumber;

        @Column(name="UserTimestamp")
        private LocalDateTime UserTimestamp = LocalDateTime.now();

        //Relationships
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Address> address;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Order> orders;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Category> categories;

        @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Cart> carts;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private  List<Payment> payments;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Product> products;

    }
