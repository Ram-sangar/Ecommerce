package com.ecommerce.demo.Cart_Items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartIdAndProductId(Integer cartId, Integer productId);

    List<CartItem> findByCartId(Integer integer);

    @Modifying
    @Transactional
    @Query(value = "delete from cart_items where id = :cartItemId", nativeQuery = true)
    void deleteOrderItemById(@Param("cartItemId") Integer cartItemId);
}
