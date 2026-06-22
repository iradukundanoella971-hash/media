package com.media.service;

import com.media.dto.CreatePostRequest;
import com.media.dto.PostResponse;
import com.media.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {

    PostResponse createPost(CreatePostRequest request);
    List<Post> getAllPosts();
    Post getPostById(Long id);
    List<Post> getPostsByAuthor(Long authorId);
    Post updatePost(
            Long id,
            CreatePostRequest request);

    void deletePostById(Long id);

    void deletePostsByAuthor(Long authorId);

    void deletePostsByPeriod(
            LocalDateTime start,
            LocalDateTime end);
}
