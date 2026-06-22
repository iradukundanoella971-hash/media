package com.media.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class CreateAuthorRequest {
    @NotBlank(message = "Full name is required")
    @Size(min = 5, message = "full name must contain at least 5 characters")
    private String fullName;
    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    private String email;
}
