package com.media.dto;
import com.media.entity.Visibility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import  jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class CreatePostRequest {
    @NotBlank(message = "title is required")
    @Size(min=10,max =100,message = "title mustcontain btn 10 and 100 char")
    private String content;

    @NotNull(message ="author id is required")
    private Long authorId;

    @NotNull(message = "Visibility required")
    private  Visibility visibility;}

