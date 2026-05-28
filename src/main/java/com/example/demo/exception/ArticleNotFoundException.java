package com.example.demo;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(Long id) {
        super("게시물을 찾을 수 없습니다. id=" + id);
    }
}
