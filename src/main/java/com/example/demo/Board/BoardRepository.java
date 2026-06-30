package com.example.demo.Board;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Repository
public class BoardRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }

    public void delete(Long id) {
        Board board = em.find(Board.class, id);
        if (board != null) {
            em.remove(board);
        }
    }

    public List<Board> findAll() {
        String jpql = "SELECT b FROM Board b";
        return em.createQuery(jpql, Board.class).getResultList();
    }

    public Board findById(Long id) {
        return em.find(Board.class, id);
    }
}