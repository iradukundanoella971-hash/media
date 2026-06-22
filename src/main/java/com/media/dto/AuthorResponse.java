package com.media.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder

public class AuthorResponse {
    private String message;
    private String fullname;
    private String username;
    private  String email;
    private LocalDateTime createdAt;

}
