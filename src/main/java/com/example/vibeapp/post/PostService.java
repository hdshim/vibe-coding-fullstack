package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostPageDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostListDto> getAllPosts() {
        return postRepository.findAll().stream()
                .sorted((p1, p2) -> p2.getNo().compareTo(p1.getNo()))
                .map(PostListDto::from)
                .toList();
    }

    public PostPageDto getPostsPaged(int page, int size) {
        List<PostListDto> allPosts = getAllPosts();
        int totalPosts = allPosts.size();
        int totalPages = (int) Math.ceil((double) totalPosts / size);

        int fromIndex = (page - 1) * size;
        if (fromIndex >= allPosts.size()) {
            return new PostPageDto(List.of(), page, totalPages);
        }
        int toIndex = Math.min(fromIndex + size, allPosts.size());
        return new PostPageDto(allPosts.subList(fromIndex, toIndex), page, totalPages);
    }

    public int getTotalPages(int size) {
        int totalPosts = postRepository.findAll().size();
        return (int) Math.ceil((double) totalPosts / size);
    }

    public PostResponseDto getPostByNo(Long no) {
        Post post = getPostEntityByNo(no);
        post.setViews(post.getViews() + 1);
        return PostResponseDto.from(post);
    }

    public PostResponseDto getPostByNoOnly(Long no) {
        return PostResponseDto.from(getPostEntityByNo(no));
    }

    private Post getPostEntityByNo(Long no) {
        return postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
    }

    public void updatePost(Long no, PostUpdateDto updateDto) {
        Post post = getPostEntityByNo(no);
        Post updatedPost = updateDto.toEntity(no, post.getCreatedAt(), post.getViews());
        postRepository.save(updatedPost);
    }

    public void deletePost(Long no) {
        postRepository.deleteByNo(no);
    }

    public void createPost(PostCreateDto createDto) {
        Long nextNo = postRepository.findAll().stream()
                .mapToLong(Post::getNo)
                .max()
                .orElse(0L) + 1;

        Post post = createDto.toEntity(nextNo);
        postRepository.save(post);
    }

    @PostConstruct
    public void initData() {
        if (!postRepository.findAll().isEmpty()) {
            return;
        }
        for (long i = 1; i <= 23; i++) {
            Post post = new Post(
                i,
                "바이브코딩 - 게심물 제목 " + i,
                "이것은 세련된 게시글 내용입니다. " + i,
                LocalDateTime.now().minusDays(30 - i),
                LocalDateTime.now().minusDays(30 - i),
                (int) (Math.random() * 1000)
            );
            postRepository.save(post);
        }
    }
}
