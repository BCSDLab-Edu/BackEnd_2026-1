package com.example.demo;


import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepository {

    private final Map<Long, Member> store = new HashMap<>();
    private long seq = 1;

    public Member save(Member member) {
        member.setId(seq++);
        store.put(member.getId(), member);
        return member;
    }

    public Collection<Member> findAll() {
        return store.values();
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public boolean existsByEmail(String email) {
        return store.values().stream()
                .anyMatch(m -> m.getEmail().equals(email));
    }

    public void deleteById(Long id) {
        store.remove(id);
    }
}
