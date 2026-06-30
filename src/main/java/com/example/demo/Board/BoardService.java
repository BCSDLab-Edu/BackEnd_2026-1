package com.example.demo.Board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boards;

    public BoardService(BoardRepository boardRepository) {
        this.boards = boardRepository;
    }

    @Transactional(readOnly = true)
    public List<Board> getBoards() {
        return boards.findAll();
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        return boards.findById(id);
    }

    @Transactional
    public void createBoard(String boardName) {
        Board board = new Board(boardName);
        boards.save(board);
    }

    @Transactional
    public void updateBoard(Long id, Board updatedBoard) {
        Board board = boards.findById(id);
        board.modifyBoard(updatedBoard.getBoardName());
        // Dirty Checking으로 자동 업데이트됨
    }

    @Transactional
    public void deleteBoard(Long id) {
        boards.delete(id);
    }
}