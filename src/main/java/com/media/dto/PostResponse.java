package com.media.dto;

import com.media.entity.Visibility;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private String message;
    private String title;
    private String content;
    private Visibility visibility;
    private LocalDateTime createdAt;
    private String createdBy;
}
