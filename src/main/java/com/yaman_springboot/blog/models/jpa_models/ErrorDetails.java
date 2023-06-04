package com.yaman_springboot.blog.models.jpa_models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String details;
}
