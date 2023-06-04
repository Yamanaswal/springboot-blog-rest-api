package com.yaman_springboot.blog.controllers;

import com.yaman_springboot.blog.dtos.OrderRequest;
import com.yaman_springboot.blog.dtos.OrderResponse;
import com.yaman_springboot.blog.services.order_service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.placeOrder(orderRequest));
    }


}
