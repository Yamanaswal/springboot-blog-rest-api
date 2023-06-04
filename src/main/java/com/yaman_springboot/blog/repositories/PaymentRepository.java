package com.yaman_springboot.blog.repositories;

import com.yaman_springboot.blog.models.jpa_models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {


}
