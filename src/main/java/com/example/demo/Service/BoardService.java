package com.example.demo.Service;

import com.example.demo.Exception.DeleteRestrictionException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Board;
import com.example.demo.Repository.ArticleRepository;
import com.example.demo.Repository.BoardRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;
    private int idCount = 0;

    public BoardService(BoardRepository boardRepository, ArticleRepository articleRepository) {
        this.boardRepository = boardRepository;
        this.articleRepository = articleRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Integer id) {
        Board board = boardRepository.findById(id);
        if (board == null) {
            throw new ResourceNotFoundException("해당 ID의 게시판을 찾을 수 없습니다.");
        }
        return board;
    }

    public Board createBoard(Board board) {
        idCount++;
        board.setId(idCount);
        return boardRepository.save(board);
    }

    public Board updateBoard(Integer id, Board updateData) {
        Board board = boardRepository.findById(id);
        if (board != null) {
            board.setName(updateData.getName());
            board.setDescription(updateData.getDescription());
            return boardRepository.save(board);
        }
        return null;
    }

    public boolean deleteBoard(Integer id) {
        if (!boardRepository.existsById(id)) {
            throw new ResourceNotFoundException("게시판을 찾을 수 없습니다.");
        }
        boolean hasArticles = articleRepository.findAll().stream().anyMatch(article ->
                id.equals(article.getBoardId()));
        if (hasArticles) {
            throw new DeleteRestrictionException("게시판에 작성된 게시물이 존재하여 삭제할 수 없습니다.");
        }

        boardRepository.deleteById(id);
        return hasArticles;
    }
}
