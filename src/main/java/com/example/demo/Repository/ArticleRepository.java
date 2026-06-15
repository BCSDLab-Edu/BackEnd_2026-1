package com.example.demo.Repository;

import com.example.demo.Model.Article;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ArticleRepository {
    private final Map<Long, Article> articles = new HashMap<>();
    private long seq = 1;

    public Article save(Article article) {
        article.setId(seq++);
        articles.put(article.getId(), article);
        return article;
    }

    public Article saveWithId(Article article) {
        articles.put(article.getId(), article);
        return article;
    }

    public Collection<Article> findAll() {
        return articles.values();
    }

    public Article findById(Long id) {
        return articles.get(id);
    }

    public void deleteById(Long id) {
        articles.remove(id);
    }

    public boolean existsByMemberId(Long memberId) {
        return articles.values().stream()
                .anyMatch(a -> memberId.equals(a.getMemberId()));
    }

    public boolean existsByBoardId(Long boardId) {
        return articles.values().stream()
                .anyMatch(a -> boardId.equals(a.getBoardId()));
    }
}
