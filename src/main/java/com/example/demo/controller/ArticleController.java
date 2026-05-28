package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
public class ArticleController {

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> create(@Valid @RequestBody Article article) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(article));
    }

    @GetMapping("/articles")
    public Collection<Article> getAll() {
        return service.getAll();
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody Article req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
