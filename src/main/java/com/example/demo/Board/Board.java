package com.example.demo.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "board")
@Getter @Setter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String boardName;

    public Board(String boardName) {
        this.boardName = boardName;
    }

    public void modifyBoard(String modifiedBoardName) {
        this.boardName = modifiedBoardName;
    }
}