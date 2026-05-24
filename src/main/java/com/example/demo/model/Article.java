package com.example.demo.model;

import java.time.LocalDateTime;

public class Article {

    private int id;
    private int authorId;
    private int boardId;

    private String author;
    private String title;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Article() {
    }

    public Article(
            int id,
            int authorId,
            int boardId,
            String author,
            String title,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.authorId = authorId;
        this.boardId = boardId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
