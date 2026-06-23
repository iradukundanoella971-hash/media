package com.media.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePostRequest {
    @NotBlank(message = "title is required")
    @Size(min = 10, max = 100, message = "Title must contain between 10 and 100 characters")
    private String title;

    @NotBlank(message = "content is required")
    @Size(min = 50, max = 2000, message = "Content must contain between 50 and 2000 characters")
    private String content;

    @NotNull(message = "author id is required")
    private Long authorId;
}

