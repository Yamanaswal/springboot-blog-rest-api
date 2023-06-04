package com.yaman_springboot.blog.repositories;

import com.yaman_springboot.blog.models.jpa_models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
