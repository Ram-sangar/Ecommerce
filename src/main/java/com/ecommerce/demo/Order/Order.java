package com.ecommerce.demo.Order;

import com.ecommerce.demo.OrderItem.OrderItem;
import com.ecommerce.demo.Users.User;
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
@Table(name="Orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @Column(nullable = false)
    private Long orderToken;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private LocalDateTime orderTimestamp=LocalDateTime.now();

    @OneToMany
    private List<OrderItem> orderItem;

}
