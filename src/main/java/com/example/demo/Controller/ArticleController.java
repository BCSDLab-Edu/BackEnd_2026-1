package com.example.demo.Controller;

import com.example.demo.Model.Article;
import com.example.demo.Service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Map;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @ResponseBody
    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable int id) {
        return articleService.getArticle(id);
    }

    @ResponseBody
    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("boardName", "Free Board");
        model.addAttribute("articles", articleService.getAllArticles());
        return "posts";
    }

    @ResponseBody
    @PostMapping("/article")
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @ResponseBody
    @PutMapping("/article/{id}")
    public Article updateArticle(@PathVariable int id, @RequestBody Article article) {
        return articleService.updateArticle(id, article);
    }

    @ResponseBody
    @DeleteMapping("/article/{id}")
    public Map<String, String> deleteArticle(@PathVariable int id) {
        return articleService.deleteArticle(id);
    }
}
