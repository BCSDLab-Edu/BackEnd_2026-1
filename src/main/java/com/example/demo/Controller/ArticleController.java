package com.example.demo.Controller;

import com.example.demo.Model.Article;
import com.example.demo.Service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String getPostsView(Model model) {
        model.addAttribute("boardName", "자유게시판");
        model.addAttribute("articles", articleService.getAllArticles());
        return "posts";
    }

    @GetMapping("/articles")
    @ResponseBody
    public List<Article> getArticles(@RequestParam(value = "boardId", required = false) Integer boardId) {
        if (boardId != null) {
            return articleService.getArticlesByBoardId(boardId);
        }
        return articleService.getAllArticles();
    }

    @GetMapping("/article/{id}")
    @ResponseBody
    public ResponseEntity<Article> getArticle(@PathVariable Integer id) {
        Article article = articleService.getArticle(id);
        return ResponseEntity.ok(article);
    }

    @PostMapping("/article")
    @ResponseBody
    public ResponseEntity<Article> postArticle(@Valid @RequestBody Article article) {
        Article created = articleService.postArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/article/{id}")
    @ResponseBody
    public ResponseEntity<Article> putArticle(@PathVariable Integer id, @RequestBody Article update) {
        Article updated = articleService.updateArticle(id, update);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/article/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
        if (!articleService.deleteArticle(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
