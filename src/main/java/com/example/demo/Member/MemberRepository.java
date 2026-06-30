package com.example.demo.Member;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public void delete(Long id) {
        Member member = em.find(Member.class, id);
        if (member != null) {
            em.remove(member);
        }
    }

    public List<Member> findAll() {
        String jpql = "SELECT m FROM Member m";
        return em.createQuery(jpql, Member.class).getResultList();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public boolean existsByEmailAddress(String emailAddress) {
        String jpql = "SELECT COUNT(m) FROM Member m WHERE m.emailAddress = :emailAddress";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("emailAddress", emailAddress)
                .getSingleResult();
        return count > 0;
    }
}