package com.yaman_springboot.blog.controllers;


import com.yaman_springboot.blog.dtos.CommentDto;
import com.yaman_springboot.blog.services.comment_service.CommentService;
import jakarta.validation.Valid;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("create-comment")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(commentDto.getPost_id(),commentDto), HttpStatus.CREATED);
    }

    @GetMapping("get-comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@RequestParam("post_id") long postId){
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId),HttpStatus.OK);
    }

    @GetMapping("get-comment")
    public ResponseEntity<CommentDto> getCommentByCommentId(@RequestParam("post_id") long postId, @RequestParam("comment_id") long comment_id){
        return new ResponseEntity<>(commentService.getCommentByCommentId(postId,comment_id),HttpStatus.OK);
    }

    @PutMapping("update-comment")
    public ResponseEntity<CommentDto> updateComment(@RequestParam("post_id") long postId, @RequestParam("comment_id") long comment_id,@Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.updateComment(postId,comment_id,commentDto),HttpStatus.OK);
    }

    @DeleteMapping("delete-comment")
    public ResponseEntity<String> deleteComment(@RequestParam("post_id") long postId, @RequestParam("comment_id") long comment_id){
        commentService.deleteComment(postId,comment_id);
        return new ResponseEntity<>("Delete Comment Successfully.",HttpStatus.OK);
    }
}
