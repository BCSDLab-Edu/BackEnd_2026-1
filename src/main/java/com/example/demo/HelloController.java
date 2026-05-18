package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HelloController {

    private final Map<Long, Article> articles = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "hello";
    }

    @GetMapping("/introduce")
    public String introduce(@RequestParam(required = false) String name, Model model) {
        if (name == null || name.isBlank()) {
            model.addAttribute("message", "안녕하세요 제 이름은 이재훈입니다!");
        } else {
            model.addAttribute("message", "안녕하세요 제 이름은 " + name + "입니다!");
        }

        return "introduce";
    }

    @ResponseBody
    @GetMapping("/json")
    public Map<String, Object> json() {
        return Map.of(
                "age", 20,
                "name", "이재훈"
        );
    }

    @ResponseBody
    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articles.get(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(article);
    }

    @ResponseBody
    @PostMapping("/article")
    public ResponseEntity<Article> createArticle(@RequestBody ArticleRequest request) {
        Long id = idGenerator.getAndIncrement();

        Article article = new Article(id, request.getDescription());
        articles.put(id, article);

        return ResponseEntity.status(201).body(article);
    }

    @ResponseBody
    @PutMapping("/article/{id}")
    public ResponseEntity<Article> updateArticle(
            @PathVariable Long id,
            @RequestBody ArticleRequest request
    ) {
        if (!articles.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        Article article = new Article(id, request.getDescription());
        articles.put(id, article);

        return ResponseEntity.ok(article);
    }

    @ResponseBody
    @DeleteMapping("/article/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (!articles.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        articles.remove(id);

        return ResponseEntity.noContent().build();
    }

    static class Article {
        private Long id;
        private String description;

        public Article(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public Long getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }
    }

    static class ArticleRequest {
        private String description;

        public ArticleRequest() {
        }

        public String getDescription() {
            return description;
        }
    }
}
