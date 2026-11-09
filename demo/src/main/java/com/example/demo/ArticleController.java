package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final Map<Long, Article> articles = new HashMap<>();
    private Long nextId = 1L;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Article article) {
        articles.put(nextId, article);
        nextId++;
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
