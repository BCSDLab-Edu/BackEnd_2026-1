package com.example.demo.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class Article {
    private Integer id;
    @NotNull(message = "게시판 ID는 필수입니다.")
    private Integer boardId;
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    @NotNull(message = "작성자 ID는 필수입니다.")
    private Integer memberId;
    private LocalDateTime createdAt;
    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    public Article() {}

    public Article(Integer id, String title, String content) {
        this.id = id;
        this.boardId = boardId;
        this.title = title;
        this.memberId = memberId;
        this.createdAt = LocalDateTime.now();
        this.content = content;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBoardId() { return boardId; }
    public void setBoardId(Integer boardId) { this.boardId = boardId; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMemberId() {
        return memberId;
    }
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
