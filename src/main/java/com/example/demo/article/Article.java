package com.example.demo.article;

public class Article {

    private Long id;
    private Long authorId;
    private Long boardId;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;

    public Article(Long id, Long authorId, Long boardId, String title, String content,
                   String createdAt, String updatedAt) {
        this.id = id;
        this.authorId = authorId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
