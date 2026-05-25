package com.example.demo.article;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleRepository {

    private final Map<Long, Article> articles = new LinkedHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ArticleRepository() {
        save(new ArticleRequestData(1L, 1L, "제목0", "내용입니다."));
        save(new ArticleRequestData(1L, 1L, "제목1", "내용입니다 내용입니다."));
        save(new ArticleRequestData(1L, 1L, "제목2", "내용입니다 내용입니다 내용입니다."));
    }

    public Collection<Article> findAll() {
        return articles.values();
    }

    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(articles.get(id));
    }

    public Article save(ArticleRequestData request) {
        Long id = idGenerator.getAndIncrement();
        String now = LocalDate.now().toString();

        Article article = new Article(
                id,
                request.authorId(),
                request.boardId(),
                request.title(),
                request.content(),
                now,
                now
        );

        articles.put(id, article);
        return article;
    }

    public Optional<Article> update(Long id, ArticleRequestData request) {
        Article oldArticle = articles.get(id);

        if (oldArticle == null) {
            return Optional.empty();
        }

        Article updatedArticle = new Article(
                id,
                request.authorId(),
                request.boardId(),
                request.title(),
                request.content(),
                oldArticle.getCreatedAt(),
                LocalDate.now().toString()
        );

        articles.put(id, updatedArticle);
        return Optional.of(updatedArticle);
    }

    public boolean delete(Long id) {
        return articles.remove(id) != null;
    }

    public record ArticleRequestData(Long authorId, Long boardId, String title, String content) {
    }
}