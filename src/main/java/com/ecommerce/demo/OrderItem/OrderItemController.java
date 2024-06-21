package com.ecommerce.demo.OrderItem;

import com.ecommerce.demo.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {
    @Autowired
    com.ecommerce.demo.OrderItem.OrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderItem orderItem){
        /*if(! orderService.findId(order.getUserId())) {
            return new ResponseEntity<>(new ApiResponse(false,"user id does not exist"),HttpStatus.NOT_FOUND);}*/
        orderItemService.createorderItem(orderItem);
        return new ResponseEntity<>(new ApiResponse(true,"create a new order"), HttpStatus.CREATED);
    }

    @GetMapping("/List")
    public ResponseEntity<List<OrderItem>> listOrder(){
        List<OrderItem> orderItemList = orderItemService.listorderItem();
        return ResponseEntity.ok(orderItemList);
    }

    @GetMapping("/List/{orderId}")
    public Optional<OrderItem> findById(@PathVariable("orderItemId") long orderItemId){
        return orderItemService.findById(orderItemId);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteById(@PathVariable("orderItemId") long orderItemid){
        System.out.println("delete by orderItemid "+ orderItemid);
        orderItemService.deleteByid(orderItemid);
        return ResponseEntity.ok("delete success");
    }


}
