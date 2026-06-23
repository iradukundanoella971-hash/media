package com.media.service;

import com.media.dto.CreatePostRequest;
import com.media.dto.PostResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {

    PostResponse createPost(CreatePostRequest request);
    List<PostResponse> getAllPosts();
    PostResponse getPostById(Long id);
    List<PostResponse> getPostsByAuthor(Long authorId);
    PostResponse updatePost(
            Long id,
            CreatePostRequest request);

    void deletePostById(Long id);

    void deletePostsByAuthor(Long authorId);

    void deletePostsByPeriod(
            LocalDateTime start,
            LocalDateTime end);
}
