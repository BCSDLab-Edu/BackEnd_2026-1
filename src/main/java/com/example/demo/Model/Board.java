package com.example.demo.Model;

import jakarta.validation.constraints.NotBlank;

public class Board {
    private Integer id;
    @NotBlank(message = "게시판 이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "게시판 설명은 필수 입력 값입니다.")
    private String description;

    public Board() {}

    public Board(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description =description;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
