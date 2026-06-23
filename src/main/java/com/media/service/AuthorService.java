package com.media.service;

import com.media.dto.AuthorResponse;
import com.media.dto.CreateAuthorRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface AuthorService {
    AuthorResponse createAuthor(CreateAuthorRequest request);
    AuthorResponse getAuthorById(Long id);
    AuthorResponse getAuthorByUsername(String username);
    AuthorResponse getAuthorByEmail(String email);

    List<AuthorResponse> getAllAuthors();
    List<AuthorResponse> getAuthorsCreatedBetween(
            LocalDateTime start,
            LocalDateTime end);

    AuthorResponse updateBio(Long id, String bio);
    AuthorResponse updateProfile(
            Long id,
            CreateAuthorRequest request);

    void deleteAuthor(Long id);
}
