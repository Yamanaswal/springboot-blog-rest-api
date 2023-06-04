package com.yaman_springboot.blog.exceptions;

public class PaymentException extends RuntimeException {

    public PaymentException(String message) {
        super(message);
    }

}
