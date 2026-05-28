package com.example.demo;

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
        if (member == null) throw new MemberNotFoundException(id);
        return member;
    }

    public Member update(Long id, Member req) {
        Member member = repository.findById(id);
        if (member == null) throw new MemberNotFoundException(id);
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
        if (member == null) throw new MemberNotFoundException(id);
        if (articleRepository.existsByMemberId(id)) {
            throw new HasArticlesException("게시물이 존재하여 삭제할 수 없습니다.");
        }
        repository.deleteById(id);
    }
}
