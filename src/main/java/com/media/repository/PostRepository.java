package com.media.repository;
import com.media.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatedById(Long authorId);
    void deleteByCreatedById(Long authorId);
    void deleteByCreatedAtBetween(
            LocalDateTime start,
            LocalDateTime end);
}
