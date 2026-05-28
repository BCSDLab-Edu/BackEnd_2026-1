package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping("/members")
    public ResponseEntity<Member> create(@Valid @RequestBody Member member) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(member));
    }

    @GetMapping("/members")
    public Collection<Member> getAll() {
        return service.getAll();
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Member> update(@PathVariable Long id, @Valid @RequestBody Member req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
