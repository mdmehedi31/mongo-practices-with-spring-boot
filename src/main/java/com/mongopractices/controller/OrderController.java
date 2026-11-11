package com.mongopractices.controller;


import com.mongopractices.dto.OrderDTO;
import com.mongopractices.entity.OrderEntity;
import com.mongopractices.service.OrderService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity order) {
        OrderEntity orderEntity = orderService.save(order);
        return ResponseEntity.ok(orderEntity);
    }


    @GetMapping("/gets/{userId}")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByUserId(@PathVariable ObjectId userId) {
        List<OrderDTO> orderDTOS = orderService.findAllOrderByUserId(userId);
        return ResponseEntity.ok(orderDTOS);
    }
}
