package com.ecommerce.demo.OrderItem;
import com.ecommerce.demo.Order.Order;
import com.ecommerce.demo.Order.OrderRepository;
import com.ecommerce.demo.OrderItem.OrderItem;
import com.ecommerce.demo.Product.Product;

import com.ecommerce.demo.Users.User;
import com.ecommerce.demo.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    com.ecommerce.demo.Order_Item.OrderItemRepository orderItemRepository;

    @Autowired
    UserRepository userRepository;

    public void createorderItem(OrderItem orderItem) {
        User user = userRepository.findById(orderItem.getUserId()).orElseThrow(()->new ExpressionException("User does not exist"));
        //orderItem.setUserId(user);
        orderItemRepository.save(orderItem);
    }

    public List<OrderItem> listorderItem() {
        return orderItemRepository.findAll();
    }


    public Optional<OrderItem> findById(long orderItemId) {
        return orderItemRepository.findById(orderItemId);
    }

    public void deleteByid(long orderItemid) {
        orderItemRepository.deleteById(orderItemid);
    }

}
