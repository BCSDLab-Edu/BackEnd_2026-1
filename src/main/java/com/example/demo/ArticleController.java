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

    @PostMapping
    public ResponseEntity<Long> createArticle(@RequestBody ArticleDto articleDto) {
        Long id = idSequence++;
        db.put(id, articleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}