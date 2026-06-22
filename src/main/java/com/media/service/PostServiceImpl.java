package com.media.service;

import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getPostsByAuthor(Long authorId) {
        return postRepository.findByCreatedById(authorId);
    }

    @Override
    public void deletePostsByAuthor(Long authorId) {
        postRepository.deleteByCreatedById(authorId);
    }
}
}
