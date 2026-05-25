package com.example.demo.article;

public class ArticleRequest {

    private Long authorId;
    private Long boardId;
    private String title;
    private String content;

    public ArticleRequest() {
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
}