package com.example.vibeapp.service;

import com.example.vibeapp.domain.Post;
import com.example.vibeapp.repository.PostRepository;
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

    public List<Post> getAllPosts() {
        return postRepository.findAll().stream()
                .sorted((p1, p2) -> p2.getNo().compareTo(p1.getNo()))
                .toList();
    }

    public Post getPostByNo(Long no) {
        Post post = getPostByNoOnly(no);
        post.setViews(post.getViews() + 1);
        return post;
    }

    public Post getPostByNoOnly(Long no) {
        return postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
    }

    public void updatePost(Long no, String title, String content) {
        Post post = getPostByNoOnly(no);
        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedAt(LocalDateTime.now());
    }

    public void addPost(String title, String content) {
        Long nextNo = postRepository.findAll().stream()
                .mapToLong(Post::getNo)
                .max()
                .orElse(0L) + 1;

        Post post = new Post(
                nextNo,
                title,
                content,
                LocalDateTime.now(),
                null,
                0
        );
        postRepository.save(post);
    }

    @PostConstruct
    public void initData() {
        for (long i = 1; i <= 10; i++) {
            Post post = new Post(
                i,
                "바이브코딩 - 게심물 제목 " + i,
                "이것은 세련된 게시글 내용입니다. " + i,
                LocalDateTime.now().minusDays(10 - i),
                LocalDateTime.now().minusDays(10 - i),
                (int) (Math.random() * 1000)
            );
            postRepository.save(post);
        }
    }
}
