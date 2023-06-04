package com.yaman_springboot.blog.services.order_service;

import com.yaman_springboot.blog.dtos.OrderRequest;
import com.yaman_springboot.blog.dtos.OrderResponse;
import com.yaman_springboot.blog.exceptions.PaymentException;
import com.yaman_springboot.blog.models.jpa_models.Order;
import com.yaman_springboot.blog.models.jpa_models.Payment;
import com.yaman_springboot.blog.repositories.OrderRepository;
import com.yaman_springboot.blog.repositories.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    OrderRepository orderRepository;
    PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderRequest.getOrder();
        order.setStatus("In-Progress");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("payment card not support.");
        }

        payment.setOrderId(order.getId());

        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("Success");

        return orderResponse;
    }
}
