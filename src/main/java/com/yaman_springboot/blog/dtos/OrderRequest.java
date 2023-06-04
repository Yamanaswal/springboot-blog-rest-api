package com.yaman_springboot.blog.dtos;

import com.yaman_springboot.blog.models.jpa_models.Order;
import com.yaman_springboot.blog.models.jpa_models.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;

}
