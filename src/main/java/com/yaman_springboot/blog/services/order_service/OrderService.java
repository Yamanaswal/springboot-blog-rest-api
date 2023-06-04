package com.yaman_springboot.blog.services.order_service;

import com.yaman_springboot.blog.dtos.OrderRequest;
import com.yaman_springboot.blog.dtos.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);

}
