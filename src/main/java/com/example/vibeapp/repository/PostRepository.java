package com.example.vibeapp.repository;

import com.example.vibeapp.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private static final List<Post> posts = new ArrayList<>();

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    public void save(Post post) {
        posts.add(post);
    }
}
