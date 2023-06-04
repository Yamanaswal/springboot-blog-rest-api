package com.yaman_springboot.blog.services.post_service;

import com.yaman_springboot.blog.dtos.PostDto;
import com.yaman_springboot.blog.dtos.PostResponse;
import com.yaman_springboot.blog.repositories.PostRepository;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto,long id);

    PostDto deletePostById(long id);
}
