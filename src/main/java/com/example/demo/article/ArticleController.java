package com.example.demo.article;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("articles", articleService.getArticles());
        return "posts";
    }

    @ResponseBody
    @GetMapping("/articles")
    public Collection<Article> getArticles() {
        return articleService.getArticles();
    }

    @ResponseBody
    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        return articleService.getArticle(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseBody
    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody ArticleRequest request) {
        Article article = articleService.createArticle(request);
        return ResponseEntity.status(201).body(article);
    }

    @ResponseBody
    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(
            @PathVariable Long id,
            @RequestBody ArticleRequest request
    ) {
        return articleService.updateArticle(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseBody
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        boolean deleted = articleService.deleteArticle(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}