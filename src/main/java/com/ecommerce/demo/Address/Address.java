package com.ecommerce.demo.Address;

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
@Table(name="Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @Column (nullable = false)
    private String unitNo;

    @Column (nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column (nullable = false)
    private String landmark;

    @Column (nullable = false)
    private String postal;

    @Column(name="addressTimestamp")
    private LocalDateTime AddressTimestamp = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" ,referencedColumnName = "userId")
    private User user;

    @Transient
    private Long userId;
}
