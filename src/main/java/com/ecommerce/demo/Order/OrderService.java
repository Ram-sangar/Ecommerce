package com.ecommerce.demo.Order;
import com.ecommerce.demo.Category.Category;
import com.ecommerce.demo.Users.User;
import com.ecommerce.demo.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public void createOrder(Order order) {
        User user = userRepository.findById(order.getUser().getUserId()).orElseThrow(()->new ExpressionException("User does not exist"));
        order.setUser(user);
        orderRepository.save(order);
    }
    //getall
    public List<Order> listorder() {
        return orderRepository.findAll();
    }

    //getbyid
    public Optional<Order> findById(long orderId) {
        return orderRepository.findById(orderId);
    }

    //delete
    public void deleteByid(long orderid) {
        orderRepository.deleteById(orderid);
    }

}
