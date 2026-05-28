package com.example.demo;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Collection;

@Service
public class ArticleService {

    private final ArticleRepository repository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public ArticleService(ArticleRepository repository,
                          MemberRepository memberRepository,
                          BoardRepository boardRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    public Article create(Article article) {
        // 존재하지 않는 사용자 참조 시 400
        if (article.getMemberId() != null &&
                memberRepository.findById(article.getMemberId()) == null) {
            throw new InvalidReferenceException("존재하지 않는 사용자입니다. id=" + article.getMemberId());
        }
        // 존재하지 않는 게시판 참조 시 400
        if (article.getBoardId() != null &&
                boardRepository.findById(article.getBoardId()) == null) {
            throw new InvalidReferenceException("존재하지 않는 게시판입니다. id=" + article.getBoardId());
        }
        article.setDate(OffsetDateTime.now());
        article.setUpdate(OffsetDateTime.now());
        return repository.save(article);
    }

    public Collection<Article> getAll() {
        return repository.findAll();
    }

    public Article getById(Long id) {
        Article article = repository.findById(id);
        if (article == null) throw new ArticleNotFoundException(id);
        return article;
    }

    public Article update(Long id, Article req) {
        Article article = repository.findById(id);
        if (article == null) throw new ArticleNotFoundException(id);
        // 존재하지 않는 사용자 참조 시 400
        if (req.getMemberId() != null &&
                memberRepository.findById(req.getMemberId()) == null) {
            throw new InvalidReferenceException("존재하지 않는 사용자입니다. id=" + req.getMemberId());
        }
        // 존재하지 않는 게시판 참조 시 400
        if (req.getBoardId() != null &&
                boardRepository.findById(req.getBoardId()) == null) {
            throw new InvalidReferenceException("존재하지 않는 게시판입니다. id=" + req.getBoardId());
        }
        article.setTitle(req.getTitle());
        article.setContent(req.getContent());
        article.setUpdate(OffsetDateTime.now());
        return article;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
