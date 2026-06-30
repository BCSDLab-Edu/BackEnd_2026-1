package com.example.demo.Article;

import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Article article) {
        em.persist(article);
    }

    public Article findById(Long id) {
        return em.find(Article.class, id);
    }

    public List<Article> findAll() {
        String jpql = "SELECT a FROM Article a";
        return em.createQuery(jpql, Article.class).getResultList();
    }

    public List<Article> findByBoardId(Long boardId) {
        String jpql = "SELECT a FROM Article a WHERE a.boardId = :boardId";
        return em.createQuery(jpql, Article.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public void update(Article article) {
        em.merge(article);
    }

    public void delete(Long id) {
        Article article = em.find(Article.class, id);
        if (article != null) {
            em.remove(article);
        }
    }
}