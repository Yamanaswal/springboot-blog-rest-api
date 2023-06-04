package com.yaman_springboot.blog.repositories;

import com.yaman_springboot.blog.dtos.CommentDto;
import com.yaman_springboot.blog.models.jpa_models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/* Note: Do not need to @Repository */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);

}
