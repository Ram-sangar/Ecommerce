/*
package com.ecommerce.demo.Payment;
import com.ecommerce.demo.Order.Order;
import com.ecommerce.demo.Users.User;
import com.ecommerce.demo.Users.UserRepository;
import com.ecommerce.demo.Order.OrderRepository;

import com.ecommerce.demo.Order.OrderRepository;
import com.ecommerce.demo.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    public void createPayment(Payment payment) {
        User user = userRepository.findById(payment.getUser().getUserId()).orElseThrow(()-> new ExpressionException("user does not exist"));
        payment.setUser(user);

        Order order = orderRepository.findById(payment.getOrder().getOrderId()).orElseThrow(()->new ExpressionException("Order does not exist"));
        payment.setOrder(order);

        paymentRepository.save(payment);
    }


}
*/
