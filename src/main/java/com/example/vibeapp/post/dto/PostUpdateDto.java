package com.example.vibeapp.post.dto;

import com.example.vibeapp.post.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class PostUpdateDto {

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(max = 100, message = "제목은 최대 100자까지 입력 가능합니다.")
    private String title;

    private String content;

    public PostUpdateDto() {}

    public PostUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public static PostUpdateDto from(Post post) {
        return new PostUpdateDto(post.getTitle(), post.getContent());
    }

    public Post toEntity(Long no, LocalDateTime createdAt, int views) {
        return new Post(
                no,
                title,
                content,
                createdAt,
                LocalDateTime.now(),
                views
        );
    }
}
