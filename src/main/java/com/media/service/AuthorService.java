package com.media.service;
import com.media.dto.AuthorResponse;
import com.media.dto.CreateAuthorRequest;
import com.media.entity.Author;
import java.time.LocalDateTime;
import java.util.List;

public interface AuthorService {
    AuthorResponse createAuthor(CreateAuthorRequest request);
    Author getAuthorById(Long id);
    Author getAuthorByUsername(String username);
    Author getAuthorByEmail(String email);

    List<Author> getAllAuthors();
    List<Author> getAuthorsCreatedBetween(
            LocalDateTime start,
            LocalDateTime end);

    Author updateBio(Long id, String bio);
    Author updateProfile(
            Long id,
            CreateAuthorRequest request);

    void deleteAuthor(Long id);

}
