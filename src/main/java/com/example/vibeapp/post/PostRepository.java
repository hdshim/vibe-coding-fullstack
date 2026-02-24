package com.example.vibeapp.post;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class PostRepository {
    private static final List<Post> posts = new CopyOnWriteArrayList<>();

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    public void save(Post post) {
        posts.add(post);
    }

    public java.util.Optional<Post> findByNo(Long no) {
        java.util.Optional<Post> result = posts.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst();
        System.out.println("Repository: findByNo(" + no + ") -> present=" + result.isPresent());
        return result;
    }

    public void deleteByNo(Long no) {
        System.out.println("Repository: Before deleteByNo(" + no + "), count=" + posts.size());
        boolean removed = posts.removeIf(post -> post.getNo().equals(no));
        System.out.println("Repository: After deleteByNo(" + no + "), count=" + posts.size() + ", removed=" + removed);
    }
}
