package com.example.demo;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(Long id) {
        super("게시판을 찾을 수 없습니다. id=" + id);
    }
}
