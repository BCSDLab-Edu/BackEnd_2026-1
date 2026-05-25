package com.example.demo.article;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Collection<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticle(Long id) {
        return articleRepository.findById(id);
    }

    public Article createArticle(ArticleRequest request) {
        return articleRepository.save(toData(request));
    }

    public Optional<Article> updateArticle(Long id, ArticleRequest request) {
        return articleRepository.update(id, toData(request));
    }

    public boolean deleteArticle(Long id) {
        return articleRepository.delete(id);
    }

    private ArticleRepository.ArticleRequestData toData(ArticleRequest request) {
        return new ArticleRepository.ArticleRequestData(
                request.getAuthorId(),
                request.getBoardId(),
                request.getTitle(),
                request.getContent()
        );
    }
}
