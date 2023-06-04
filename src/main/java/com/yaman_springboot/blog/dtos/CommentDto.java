package com.yaman_springboot.blog.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {

    private long id;
    @NotEmpty(message = "Name should not be empty or null.")
    private String name;

    @NotEmpty(message = "Email should not be empty or null.")
    @Email(message = "Please Enter Valid Email Address.")
    private String email;

    @NotEmpty
    @Size(min = 5,message = "Comment body contains al least 5 min characters.")
    private String body;

    private long post_id;
}
