package com.media.service;

import com.media.dto.CreatePostRequest;
import com.media.dto.PostResponse;
import com.media.entity.Author;
import com.media.entity.Post;
import com.media.entity.Visibility;
import com.media.exception.ResourceNotFoundException;
import com.media.repository.AuthorRepository;
import com.media.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostServiceImpl(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public PostResponse createPost(CreatePostRequest request) {
        Author author = getAuthorEntity(request.getAuthorId());

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreatedBy(author);
        post.setVisibility(Visibility.PUBLIC);

        Post savedPost = postRepository.save(post);
        return toResponse(savedPost, "Post created successfully");
    }

    @Override
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponse> responses = new ArrayList<>();

        for (Post post : posts) {
            responses.add(toResponse(post, "Post loaded successfully"));
        }

        return responses;
    }

    @Override
    public PostResponse getPostById(Long id) {
        Post post = getPostEntity(id);
        return toResponse(post, "Post found successfully");
    }

    @Override
    public List<PostResponse> getPostsByAuthor(Long authorId) {
        getAuthorEntity(authorId);

        List<Post> posts = postRepository.findByCreatedById(authorId);
        List<PostResponse> responses = new ArrayList<>();

        for (Post post : posts) {
            responses.add(toResponse(post, "Post loaded successfully"));
        }

        return responses;
    }

    @Override
    public PostResponse updatePost(Long id, CreatePostRequest request) {
        Post post = getPostEntity(id);

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        Post savedPost = postRepository.save(post);
        return toResponse(savedPost, "Post updated successfully");
    }

    @Override
    public void deletePostById(Long id) {
        Post post = getPostEntity(id);
        postRepository.delete(post);
    }

    @Override
    public void deletePostsByAuthor(Long authorId) {
        getAuthorEntity(authorId);
        postRepository.deleteByCreatedById(authorId);
    }

    @Override
    public void deletePostsByPeriod(LocalDateTime start, LocalDateTime end) {
        postRepository.deleteByCreatedAtBetween(start, end);
    }

    private Author getAuthorEntity(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
    }

    private Post getPostEntity(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }

    private PostResponse toResponse(Post post, String message) {
        PostResponse response = new PostResponse();
        response.setMessage(message);
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setVisibility(post.getVisibility());
        response.setCreatedAt(post.getCreatedAt());
        response.setCreatedBy(post.getCreatedBy() == null ? null : post.getCreatedBy().getFullName());
        return response;
    }
}
