package com.yaman_springboot.blog.services.post_service;

import com.yaman_springboot.blog.dtos.PostDto;
import com.yaman_springboot.blog.dtos.PostResponse;
import com.yaman_springboot.blog.exceptions.ResourceNotFoundException;
import com.yaman_springboot.blog.models.jpa_models.Post;
import com.yaman_springboot.blog.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    /*Note: @Autowired can be removed due only one constructor. */
    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //Convert DTO to Entity.
        Post post = mapToEntity(postDto);

        //Save to Entity and return
        Post newPost = postRepository.save(post);

        //Convert Entity to DTO.
        return mapToDto(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        //Create PostResponse Instance.
        PostResponse postResponse = new PostResponse();

        // Get All Data in all pages.
        if (pageNo <= 0) {
            List<Post> posts = postRepository.findAll();
            // Custom Response.
            List<PostDto> content = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
            postResponse.setContent(content);
            postResponse.setTotalElements(content.size());
            return postResponse;
        }
        // Get Pagination Data.
        else {

            //Create Sort instance - based on asc or desc
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                    ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();

            // Create Pageable instance.
            // page = page - 1
            // for PageRequest index start at 0.
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
            Page<Post> postPage = postRepository.findAll(pageable);
            List<Post> posts = postPage.getContent();

            List<PostDto> content = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

            postResponse.setContent(content);
            postResponse.setPageNo(postPage.getNumber() + 1); // default page start at zero index.
            postResponse.setPageSize(postPage.getSize());
            postResponse.setTotalElements(postPage.getTotalElements());
            postResponse.setTotalPages(postPage.getTotalPages());
            postResponse.setLastPage(postPage.isLast());

            return postResponse;
        }
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "Id", id)
        );
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {

        //Get Post by id from database.
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));

        post.setId(post.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post postUpdated = postRepository.save(post);

        return mapToDto(postUpdated);
    }

    @Override
    public PostDto deletePostById(long id) {
        //Get Post by id from database.
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        postRepository.delete(post);
        return mapToDto(post);
    }


    private PostDto mapToDto(Post post) {
//        PostDto postResponse = new PostDto();
//        postResponse.setId(post.getId());
//        postResponse.setTitle(post.getTitle());
//        postResponse.setContent(post.getContent());
//        postResponse.setDescription(post.getDescription());
//        return postResponse;

        //using model mapper.
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto) {
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        return modelMapper.map(postDto, Post.class);
    }

}
