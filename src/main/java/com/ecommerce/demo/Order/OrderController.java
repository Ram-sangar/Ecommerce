package com.ecommerce.demo.Order;

import com.ecommerce.demo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody Order order){
        /*if(! orderService.findId(order.getUserId())) {
            return new ResponseEntity<>(new ApiResponse(false,"user id does not exist"),HttpStatus.NOT_FOUND);}*/
        orderService.createOrder(order);
        return new ResponseEntity<>(new ApiResponse(true,"create a new order"), HttpStatus.CREATED);
    }

    @GetMapping("/List")
    public ResponseEntity<List<Order>> listOrder(){
        List<Order> orderList = orderService.listorder();
        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/List/{orderId}")
    public Optional<Order> findById(@PathVariable("orderId") long orderId){
        return orderService.findById(orderId);
    }

     @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteById(@PathVariable("orderId") long orderid){
        System.out.println("delete by orderid "+ orderid);
         orderService.deleteByid(orderid);
        return ResponseEntity.ok("delete success");
    }
}
