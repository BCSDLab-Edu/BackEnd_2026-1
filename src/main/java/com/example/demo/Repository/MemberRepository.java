package com.example.demo.Repository;

import com.example.demo.Model.Member;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {
    private final Map<Integer, Member> memberMap = new HashMap<>();

    public List<Member> findAll() {
        return new ArrayList<>(memberMap.values());
    }

    public Member findById(Integer id) {
        return memberMap.get(id);
    }

    public Member save(Member member) {
        memberMap.put(member.getId(), member);
        return member;
    }

    public void deleteById(Integer id) {
        memberMap.remove(id);
    }

    public boolean existsById(Integer id) {
        return memberMap.containsKey(id);
    }
}
