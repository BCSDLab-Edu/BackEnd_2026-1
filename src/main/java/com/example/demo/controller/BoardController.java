package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class BoardController {

    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @PostMapping("/boards")
    public ResponseEntity<Board> create(@Valid @RequestBody Board board) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(board));
    }

    @GetMapping("/boards")
    public Collection<Board> getAll() {
        return service.getAll();
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<Board> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id, @Valid @RequestBody Board req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
