package com.example.demo.Article;

import com.example.demo.Board.BoardService;
import com.example.demo.Member.Member;
import com.example.demo.Member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;
    private final BoardService boardService;

    public ArticleController(ArticleService articleService, MemberService memberService, BoardService boardService) {
        this.articleService = articleService;
        this.memberService = memberService;
        this.boardService = boardService;
    }

    @GetMapping("/posts")
    public String getArticlesView(@RequestParam Long boardId, Model model) {
        String boardTitle = boardService.getBoard(boardId).getBoardName();
        model.addAttribute("boardTitle", boardTitle);
        model.addAttribute("articles", articleService.getArticlesByBoardId(boardId));
        Map<Long, Member> memberMap = memberService.getMembers()
                .stream()
                .collect(Collectors.toMap(Member::getId, m -> m));
        model.addAttribute("memberMap", memberMap);
        return "articles";
    }

    @ResponseBody
    @GetMapping("/article/{id}")
    public ResponseEntity<?> getArticle(@PathVariable Long id) {
        if (articleService.getArticlesByBoardId(id) == null) {
            return ResponseEntity.status(404).body("존재하지 않는 게시물입니다.");
        }
        return ResponseEntity.ok(articleService.getArticlesByBoardId(id));
    }

    @ResponseBody
    @GetMapping("articles")
    public ResponseEntity<?> getArticleInBoard(@RequestParam(required = false) Long boardId) {
        if (boardId == null) {
            return ResponseEntity.ok(articleService.getArticles());
        }
        if (boardService.getBoard(boardId) == null) {
            return ResponseEntity.status(404).body("존재하지 않는 게시판입니다.");
        }
        return ResponseEntity.ok(articleService.getArticlesByBoardId(boardId));
    }

    @ResponseBody
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestParam Long boardId, @RequestParam Long memberId, @RequestParam String title,@RequestParam String description) {
        if (title == null || description == null) {
            throw new IllegalArgumentException("조건을 충족하지 못한 요청입니다.");
        }
        if (memberService.getMember(memberId) == null) {
            throw new IllegalArgumentException("게시글을 작성한 회원이 없습니다;;");
        }
        if (boardService.getBoard(boardId) == null) {
            throw new IllegalArgumentException("현재 존재하지 않는 게시판입니다.");
        }
        articleService.createArticle(boardId, memberId,title, description);
        return ResponseEntity.ok("생성 완료");
    }

    @ResponseBody
    @DeleteMapping("/article/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "삭제 완료";
    }

    @ResponseBody
    @PutMapping("/article/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {

        if (memberService.getMember(updatedArticle.getMemberId()) == null) {
            throw new IllegalArgumentException("게시글을 작성한 회원이 없습니다;;");
        }
        if (boardService.getBoard(updatedArticle.getBoardId()) == null) {
            throw new IllegalArgumentException("현재 존재하지 않는 게시판입니다.");
        }
        articleService.updateArticle(id, updatedArticle);
        return ResponseEntity.ok("수정 완료");
    }
}
