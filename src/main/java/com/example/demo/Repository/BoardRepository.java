package com.example.demo.Repository;

import com.example.demo.Model.Board;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {
    private final Map<Integer, Board> boardMap = new HashMap<>();

    public List<Board> findAll() {
        return new ArrayList<>(boardMap.values());
    }

    public Board findById(Integer id) {
        return boardMap.get(id);
    }

    public Board save(Board board) {
        boardMap.put(board.getId(), board);
        return board;
    }

    public void deleteById(Integer id) {
        boardMap.remove(id);
    }

    public boolean existsById(Integer id) {
        return boardMap.containsKey(id);
    }
}
