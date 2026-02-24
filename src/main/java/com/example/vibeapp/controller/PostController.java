package com.example.vibeapp.controller;

import com.example.vibeapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        model.addAttribute("post", postService.getPostByNo(no));
        return "post_detail";
    }

    @GetMapping("/posts/new")
    public String newForm() {
        return "post_new_form";
    }

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable("no") Long no, Model model) {
        model.addAttribute("post", postService.getPostByNoOnly(no));
        return "post_edit_form";
    }

    @PostMapping("/posts/add")
    public String add(@RequestParam("title") String title, @RequestParam("content") String content) {
        postService.addPost(title, content);
        return "redirect:/posts";
    }
}
