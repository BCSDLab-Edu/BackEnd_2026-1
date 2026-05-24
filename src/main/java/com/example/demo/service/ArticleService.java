package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(
            ArticleRepository articleRepository
    ) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article getArticle(int id) {
        return articleRepository.findById(id);
    }

    public Article createArticle(
            Article article
    ) {
        return articleRepository.save(article);
    }

    public Article updateArticle(
            int id,
            Article article
    ) {
        return articleRepository.update(id, article);
    }

    public boolean deleteArticle(int id) {
        return articleRepository.deleteById(id);
    }
}
