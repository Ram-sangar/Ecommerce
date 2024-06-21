package com.ecommerce.demo.Category;

import com.ecommerce.demo.Product.Product;
import com.ecommerce.demo.Users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="categories")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @Column(nullable = false)
    private String categoryName;

    @Column
    private String categoryDescription;

    @Column(nullable = false)
    private String imageurl;

    @Column(name="categoryTimestamp",nullable = false)
    private LocalDateTime categortTimstamp = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" ,referencedColumnName = "userId")
    private User user;

    @Transient
    private long userId;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

}
