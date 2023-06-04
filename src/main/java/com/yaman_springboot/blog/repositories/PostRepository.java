package com.yaman_springboot.blog.repositories;

import com.yaman_springboot.blog.models.jpa_models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/* Note: Do not need to @Repository */
public interface PostRepository extends JpaRepository<Post,Long> {

}
