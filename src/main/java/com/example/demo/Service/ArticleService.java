package com.example.demo.Service;

import com.example.demo.Model.Article;
import com.example.demo.Repository.ArticleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {
    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public Article createArticle(Article article) {
        article.setCreatedAt(LocalDate.now());
        return repository.save(article);
    }

    public Article updateArticle(int id, Article article) {
        long articleId = id;
        if (repository.findById(articleId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        article.setId(articleId);
        repository.saveWithId(article);
        return article;
    }

    public Collection<Article> getAllArticles() {
        return repository.findAll();
    }

    public Article getArticle(int id) {
        Article article = repository.findById((long) id);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return article;
    }

    public Map<String, String> deleteArticle(int id) {
        repository.deleteById((long) id);
        Map<String, String> result = new HashMap<>();
        result.put("message", "Deleted.");
        return result;
    }

    public List<String> getAllAuthors() {
        return repository.findAll()
                .stream()
                .map(Article::getAuthor)
                .toList();
    }
}
