package com.example.demo.Board;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {


    private final BoardService boardService;
    private final BoardRepository boardRepository;

    public BoardController(BoardService boardService, BoardRepository boardRepository) {
        this.boardService = boardService;
        this.boardRepository = boardRepository;
    }

    //모든게시판 조회
    @ResponseBody
    @GetMapping("/boards")
    public List<Board> getBoards() {
        return boardService.getBoards();
    }

    //특정게시판 조회
    @ResponseBody
    @GetMapping("/boards/{id}")
    public ResponseEntity<?> getArticle(@PathVariable Long id) {
        if (boardService.getBoard(id) == null) {
            return ResponseEntity.status(404).body("존재하지 않는 게시판입니다.");
        }
        return ResponseEntity.ok(boardService.getBoard(id));
    }

    //게시판 추가
    @ResponseBody
    @PostMapping("/boards")
    public ResponseEntity<?> createArticle(@RequestParam String boardName) {
        if (boardName == null) {
            return ResponseEntity.status(400).body("조건을 충족하지 못한 요청입니다.");
        }
        boardService.createBoard(boardName);
        return ResponseEntity.ok("생성 완료");
    }

    //게시판 삭제
    @ResponseBody
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        if (!boardRepository.findAll().isEmpty()) {
            return ResponseEntity.status(400).body("게시판에 개시글이 있습니다! (어딜!)");
        }
        boardService.deleteBoard(id);
        return ResponseEntity.ok("삭제완료");
    }

    //게시판 수정
    @ResponseBody
    @PutMapping("/boards/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody Board updatedBoard) {
        boardService.updateBoard(id, updatedBoard);
        return "수정 완료";
    }

}
