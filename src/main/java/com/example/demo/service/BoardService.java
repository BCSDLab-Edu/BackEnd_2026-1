package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BoardService {

    private final BoardRepository repository;
    private final ArticleRepository articleRepository;

    public BoardService(BoardRepository repository, ArticleRepository articleRepository) {
        this.repository = repository;
        this.articleRepository = articleRepository;
    }

    public Board create(Board board) {
        return repository.save(board);
    }

    public Collection<Board> getAll() {
        return repository.findAll();
    }

    public Board getById(Long id) {
        Board board = repository.findById(id);
        if (board == null) throw new BoardNotFoundException(id);
        return board;
    }

    public Board update(Long id, Board req) {
        Board board = repository.findById(id);
        if (board == null) throw new BoardNotFoundException(id);
        board.setName(req.getName());
        return board;
    }

    public void delete(Long id) {
        Board board = repository.findById(id);
        if (board == null) throw new BoardNotFoundException(id);
        if (articleRepository.existsByBoardId(id)) {
            throw new HasArticlesException("게시물이 존재하여 삭제할 수 없습니다.");
        }
        repository.deleteById(id);
    }
}
