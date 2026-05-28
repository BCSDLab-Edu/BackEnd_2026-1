package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ArticleRepository {

    private final Map<Long, Article> store = new HashMap<>();
    private long seq = 1;

    public Article save(Article article) {
        article.setId(seq++);
        store.put(article.getId(), article);
        return article;
    }

    public Collection<Article> findAll() {
        return store.values();
    }

    public Article findById(Long id) {
        return store.get(id);
    }

    public void deleteById(Long id) {
        store.remove(id);
    }

    public boolean existsByMemberId(Long memberId) {
        return store.values().stream()
                .anyMatch(a -> memberId.equals(a.getMemberId()));
    }

    public boolean existsByBoardId(Long boardId) {
        return store.values().stream()
                .anyMatch(a -> boardId.equals(a.getBoardId()));
    }

}
