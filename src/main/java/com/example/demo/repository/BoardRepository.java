package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BoardRepository {

    private final Map<Long, Board> store = new HashMap<>();
    private long seq = 1;

    public Board save(Board board) {
        board.setId(seq++);
        store.put(board.getId(), board);
        return board;
    }

    public Collection<Board> findAll() {
        return store.values();
    }

    public Board findById(Long id) {
        return store.get(id);
    }

    public void deleteById(Long id) {
        store.remove(id);
    }
}
