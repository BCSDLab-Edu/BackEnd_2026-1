package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(
            ArticleService articleService
    ) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(
            @PathVariable int id
    ) {
        Article article = articleService.getArticle(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(
            @RequestBody Article article
    ) {
        Article createdArticle = articleService.createArticle(article);

        return ResponseEntity
                .status(201)
                .body(createdArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(
            @PathVariable int id,
            @RequestBody Article article
    ) {
        Article updatedArticle =
                articleService.updateArticle(id, article);

        if (updatedArticle == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(
            @PathVariable int id
    ) {
        boolean deleted =
                articleService.deleteArticle(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
