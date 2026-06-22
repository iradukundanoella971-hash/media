package com.media.dto;
import com.media.entity.Visibility;
import lombok.Builder;
import  lombok.Data;
import java.time.LocalDateTime;
@Data
@Builder
public class PostResponse {
    private String message;
    private String title;
    private  String content;
    private Visibility visibility;
    private LocalDateTime createdAt;


}
