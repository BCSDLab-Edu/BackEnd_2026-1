package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private Map<Long, ArticleDto> db = new ConcurrentHashMap<>();
    private Long idSequence = 1L;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable Long id) {
        ArticleDto article = db.get(id);
        if (article == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<Long> createArticle(@RequestBody ArticleDto articleDto) {
        Long id = idSequence++;
        db.put(id, articleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(@RequestBody ArticleDto articleDto, @PathVariable Long id) {
        if (!db.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
        }
        db.put(id, articleDto);
        return ResponseEntity.ok("Successfully updated");
    }
}