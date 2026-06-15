package com.example.demo.Service;

import com.example.demo.Model.Member;
import com.example.demo.Repository.ArticleRepository;
import com.example.demo.Repository.MemberRepository;
import com.example.demo.exception.DuplicateEmailException;
import com.example.demo.exception.HasArticleException;
import com.example.demo.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MemberService {
    private final MemberRepository repository;
    private final ArticleRepository articleRepository;

    public MemberService(MemberRepository repository, ArticleRepository articleRepository) {
        this.repository = repository;
        this.articleRepository = articleRepository;
    }

    public Member create(Member member) {
        return repository.save(member);
    }

    public Collection<Member> getAll() {
        return repository.findAll();
    }

    public Member getById(Long id) {
        Member member = repository.findById(id);
        if (member == null) {
            throw new MemberNotFoundException(id);
        }
        return member;
    }

    public Member update(Long id, Member req) {
        Member member = repository.findById(id);
        if (member == null) {
            throw new MemberNotFoundException(id);
        }
        if (!member.getEmail().equals(req.getEmail()) &&
                repository.existsByEmail(req.getEmail())) {
            throw new DuplicateEmailException(req.getEmail());
        }
        member.setName(req.getName());
        member.setEmail(req.getEmail());
        member.setPassword(req.getPassword());
        return member;
    }

    public void delete(Long id) {
        Member member = repository.findById(id);
        if (member == null) {
            throw new MemberNotFoundException(id);
        }
        if (articleRepository.existsByMemberId(id)) {
            throw new HasArticleException("Cannot delete member because articles exist.");
        }
        repository.deleteById(id);
    }
}
