package com.yaman_springboot.blog.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;

    // title should not be null or empty
    // title has at least 2 character.
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    // post description should not be null or empty
    // post description has at least 10 character.
    @NotEmpty
    @Size(min = 10,message = "Post description should have at least 10 characters")
    private String description;

    // post content should not be null or empty
    @NotEmpty(message = "Content Should Not Be Null or Empty.")
    private String content;

    private Set<CommentDto> comments;
}
