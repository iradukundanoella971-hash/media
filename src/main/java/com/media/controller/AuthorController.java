package com.media.controller;

import com.media.dto.AuthorResponse;
import com.media.dto.CreateAuthorRequest;
import com.media.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public AuthorResponse createAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        return authorService.createAuthor(request);
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/username/{username}")
    public AuthorResponse getAuthorByUsername(@PathVariable String username) {
        return authorService.getAuthorByUsername(username);
    }

    @GetMapping("/email/{email}")
    public AuthorResponse getAuthorByEmail(@PathVariable String email) {
        return authorService.getAuthorByEmail(email);
    }

    @GetMapping
    public List<AuthorResponse> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/created-between")
    public List<AuthorResponse> getAuthorsCreatedBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return authorService.getAuthorsCreatedBetween(start, end);
    }

    @PutMapping("/{id}/bio")
    public AuthorResponse updateBio(@PathVariable Long id, @RequestParam String bio) {
        return authorService.updateBio(id, bio);
    }

    @PutMapping("/{id}")
    public AuthorResponse updateProfile(@PathVariable Long id, @Valid @RequestBody CreateAuthorRequest request) {
        return authorService.updateProfile(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
