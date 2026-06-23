package com.media.service;

import com.media.dto.AuthorResponse;
import com.media.dto.CreateAuthorRequest;
import com.media.entity.Author;
import com.media.exception.ResourceNotFoundException;
import com.media.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponse createAuthor(CreateAuthorRequest request) {
        checkUsernameAndEmail(null, request.getUsername(), request.getEmail());

        Author author = new Author();
        author.setFullName(request.getFullName());
        author.setUsername(request.getUsername());
        author.setEmail(request.getEmail());

        Author savedAuthor = authorRepository.save(author);
        return toResponse(savedAuthor, "Author created successfully");
    }

    @Override
    public AuthorResponse getAuthorById(Long id) {
        Author author = getAuthorEntity(id);
        return toResponse(author, "Author found successfully");
    }

    @Override
    public AuthorResponse getAuthorByUsername(String username) {
        Author author = authorRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with username: " + username));
        return toResponse(author, "Author found successfully");
    }

    @Override
    public AuthorResponse getAuthorByEmail(String email) {
        Author author = authorRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with email: " + email));
        return toResponse(author, "Author found successfully");
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorResponse> responses = new ArrayList<>();

        for (Author author : authors) {
            responses.add(toResponse(author, "Author loaded successfully"));
        }

        return responses;
    }

    @Override
    public List<AuthorResponse> getAuthorsCreatedBetween(LocalDateTime start, LocalDateTime end) {
        List<Author> authors = authorRepository.findByCreatedAtBetween(start, end);
        List<AuthorResponse> responses = new ArrayList<>();

        for (Author author : authors) {
            responses.add(toResponse(author, "Author loaded successfully"));
        }

        return responses;
    }

    @Override
    public AuthorResponse updateBio(Long id, String bio) {
        Author author = getAuthorEntity(id);
        author.setBio(bio);

        Author savedAuthor = authorRepository.save(author);
        return toResponse(savedAuthor, "Bio updated successfully");
    }

    @Override
    public AuthorResponse updateProfile(Long id, CreateAuthorRequest request) {
        Author author = getAuthorEntity(id);
        checkUsernameAndEmail(id, request.getUsername(), request.getEmail());

        author.setFullName(request.getFullName());
        author.setUsername(request.getUsername());
        author.setEmail(request.getEmail());

        Author savedAuthor = authorRepository.save(author);
        return toResponse(savedAuthor, "Author profile updated successfully");
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = getAuthorEntity(id);
        authorRepository.delete(author);
    }

    private Author getAuthorEntity(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    private void checkUsernameAndEmail(Long authorId, String username, String email) {
        Author usernameOwner = authorRepository.findByUsername(username).orElse(null);
        if (usernameOwner != null && !usernameOwner.getId().equals(authorId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        Author emailOwner = authorRepository.findByEmail(email).orElse(null);
        if (emailOwner != null && !emailOwner.getId().equals(authorId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
    }

    private AuthorResponse toResponse(Author author, String message) {
        AuthorResponse response = new AuthorResponse();
        response.setMessage(message);
        response.setFullName(author.getFullName());
        response.setUsername(author.getUsername());
        response.setEmail(author.getEmail());
        response.setCreatedAt(author.getCreatedAt());
        return response;
    }
}
