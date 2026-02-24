package com.example.vibeapp.post;

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

    public List<Post> getPostsPaged(int page, int size) {
        List<Post> allPosts = getAllPosts();
        int fromIndex = (page - 1) * size;
        if (fromIndex >= allPosts.size()) {
            return List.of();
        }
        int toIndex = Math.min(fromIndex + size, allPosts.size());
        return allPosts.subList(fromIndex, toIndex);
    }

    public int getTotalPages(int size) {
        int totalPosts = postRepository.findAll().size();
        return (int) Math.ceil((double) totalPosts / size);
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

    public void deletePost(Long no) {
        System.out.println("Service: deletePost(" + no + ") called");
        postRepository.deleteByNo(no);
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
        if (!postRepository.findAll().isEmpty()) {
            System.out.println("Service: initData skipped (already has data)");
            return;
        }
        System.out.println("Service: initData running (adding 23 posts)");
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
