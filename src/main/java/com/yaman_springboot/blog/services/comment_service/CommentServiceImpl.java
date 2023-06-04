package com.yaman_springboot.blog.services.comment_service;

import com.yaman_springboot.blog.dtos.CommentDto;
import com.yaman_springboot.blog.exceptions.BlogApiException;
import com.yaman_springboot.blog.exceptions.ResourceNotFoundException;
import com.yaman_springboot.blog.models.jpa_models.Comment;
import com.yaman_springboot.blog.models.jpa_models.Post;
import com.yaman_springboot.blog.repositories.CommentRepository;
import com.yaman_springboot.blog.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        // Map to Comment From Comment Dto
        Comment comment = mapToEntity(commentDto);

        // Retrieve post entity by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // Set Post to Comment Entity
        comment.setPost(post);

        // Save Comment Entity
        Comment newComment = commentRepository.save(comment);

        // Map to Dto
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        // Retrieve comments from post id
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentByCommentId(long postId, long comment_id) {

        //handle post and comment
        Comment comment = getPostAndComment(postId, comment_id);

        return mapToDto(comment);
    }


    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {

        //handle post and comment
        Comment comment = getPostAndComment(postId, commentId);

        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {

        //handle post and comment
        Comment comment = getPostAndComment(postId, commentId);

        commentRepository.delete(comment);
    }

    private Comment getPostAndComment(long postId, long comment_id) {
        // Retrieve post entity by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        // Retrieve comment entity by id
        Comment comment = commentRepository.findById(comment_id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "comment_id", comment_id));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to this post");
        }

        return comment;
    }

    private CommentDto mapToDto(Comment comment) {
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
//        return commentDto;

        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto) {
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
//        return comment;

        return modelMapper.map(commentDto, Comment.class);
    }


}
