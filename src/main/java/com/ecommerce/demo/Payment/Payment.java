package com.ecommerce.demo.Payment;

import com.ecommerce.demo.Order.Order;
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
@Table(name = "payments")

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long transcationId;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="orderId",nullable = false)
    private Order order;

    @Transient
    private Long orderId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" ,referencedColumnName = "userId")
    private User user;

    @Transient
    private long userId;

    @Enumerated
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime paymentTimestamp = LocalDateTime.now();

}
