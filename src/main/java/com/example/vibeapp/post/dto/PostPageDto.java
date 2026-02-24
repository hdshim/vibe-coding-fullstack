package com.example.vibeapp.post.dto;

import java.util.List;

public record PostPageDto(
        List<PostListDto> posts,
        int currentPage,
        int totalPages
) {}
