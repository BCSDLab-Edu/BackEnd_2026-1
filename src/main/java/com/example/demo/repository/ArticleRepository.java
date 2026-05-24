package com.example.demo.repository;

import com.example.demo.model.Article;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ArticleRepository {

    private final List<Article> articles =
            new ArrayList<>();

    private final AtomicInteger nextId =
            new AtomicInteger(1);

    public ArticleRepository() {

        save(new Article(
                0,
                1,
                1,
                "회원0",
                "제목0",
                "",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        save(new Article(
                0,
                2,
                1,
                "회원1",
                "제목1",
                "내용입니다!!",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        save(new Article(
                0,
                3,
                1,
                "회원2",
                "제목2",
                "내용입니다!!내용입니다!!",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));
    }

    public List<Article> findAll() {
        return articles;
    }

    public Article findById(int id) {

        for (Article article : articles) {

            if (article.getId() == id) {
                return article;
            }
        }

        return null;
    }

    public Article save(Article article) {

        article.setId(
                nextId.getAndIncrement()
        );

        article.setCreatedAt(
                LocalDateTime.now()
        );

        article.setUpdatedAt(
                LocalDateTime.now()
        );

        articles.add(article);

        return article;
    }

    public Article update(
            int id,
            Article newArticle
    ) {

        Article article = findById(id);

        if (article == null) {
            return null;
        }

        article.setAuthorId(
                newArticle.getAuthorId()
        );

        article.setBoardId(
                newArticle.getBoardId()
        );

        article.setAuthor(
                newArticle.getAuthor()
        );

        article.setTitle(
                newArticle.getTitle()
        );

        article.setContent(
                newArticle.getContent()
        );

        article.setUpdatedAt(
                LocalDateTime.now()
        );

        return article;
    }

    public boolean deleteById(int id) {

        Article article = findById(id);

        if (article == null) {
            return false;
        }

        articles.remove(article);

        return true;
    }
}
