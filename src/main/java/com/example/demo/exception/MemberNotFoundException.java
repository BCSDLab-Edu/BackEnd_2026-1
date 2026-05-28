package com.example.demo;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super("사용자를 찾을 수 없습니다. id=" + id);
    }
}
