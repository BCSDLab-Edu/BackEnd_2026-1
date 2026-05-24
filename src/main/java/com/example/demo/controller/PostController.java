package com.example.demo.controller;

import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    private final ArticleService articleService;

    public PostController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("boardName", "자유게시판");
        model.addAttribute("articles", articleService.getArticles());

        return "posts";
    }
}
