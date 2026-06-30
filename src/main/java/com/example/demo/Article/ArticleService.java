package com.example.demo.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articles;

    public ArticleService(ArticleRepository articleRepository) {
        this.articles = articleRepository;
    }

    @Transactional(readOnly = true)
    public List<Article> getArticles() {
        return articles.findAll();
    }

    @Transactional(readOnly = true)
    public List<Article> getArticlesByBoardId(Long boardId) {
        return articles.findByBoardId(boardId);
    }

    @Transactional
    public void createArticle(Long boardId, Long memberId, String title, String description) {
        Article article = new Article(title, description, memberId);
        articles.save(article);
    }

    @Transactional
    public void updateArticle(Long id, Article updatedArticle) {
        Article article = articles.findById(id);

        article.setTitle(updatedArticle.getTitle());
        article.setDescription(updatedArticle.getDescription());
        article.setModifiedDate(LocalDateTime.now());

        // em.merge(article) 또는 articles.update() 호출 불필요
        // 트랜잭션 커밋 시 자동으로 UPDATE 쿼리 실행됨
    }

    @Transactional
    public void deleteArticle(Long id) {
        articles.delete(id);
    }
}