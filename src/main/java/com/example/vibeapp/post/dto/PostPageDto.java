package com.example.vibeapp.post.dto;

import java.util.List;

public class PostPageDto {
    private List<PostListDto> posts;
    private int currentPage;
    private int totalPages;

    public PostPageDto(List<PostListDto> posts, int currentPage, int totalPages) {
        this.posts = posts;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<PostListDto> getPosts() { return posts; }
    public int getCurrentPage() { return currentPage; }
    public int getTotalPages() { return totalPages; }
}
