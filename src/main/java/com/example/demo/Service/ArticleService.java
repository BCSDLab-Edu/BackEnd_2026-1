package com.example.demo.Service;

import com.example.demo.Exception.InvalidReferenceException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Article;
import com.example.demo.Repository.ArticleRepository;
import com.example.demo.Repository.BoardRepository;
import com.example.demo.Repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private int idCount = 0;

    public ArticleService(ArticleRepository articleRepository, MemberRepository memberRepository, BoardRepository boardRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public List<Article> getArticlesByBoardId(Integer boardId) {
        return articleRepository.findByBoardId(boardId);
    }

    public Article getArticle(Integer id) {
        Article article = articleRepository.findById(id);
        if (article == null) {
            throw new ResourceNotFoundException("해당 ID의 게시물을 찾을 수 없습니다.");
        }
        return article;
    }

    public Article postArticle(Article article) {
        if (!memberRepository.existsById(article.getMemberId())) {
            throw new InvalidReferenceException("존재하지 않는 사용자 입니다.");
        }
        if (!boardRepository.existsById((article.getBoardId()))) {
            throw new InvalidReferenceException("존재하지 않는 게시판입니다.");
        }
        idCount++;
        article.setId(idCount);
        article.setCreatedAt(LocalDateTime.now());
        return articleRepository.save(article);
    }

    public Article updateArticle(Integer id, Article updateData) {
        Article article = articleRepository.findById(id);
        if (article == null) {
            throw new ResourceNotFoundException("게시물을 찾을 수 없습니다.");
        }
        if (!memberRepository.existsById(updateData.getMemberId())) {
            throw new InvalidReferenceException("존재하지 않는 사용자를 참조하고 있습니다.");
        }
        if (!boardRepository.existsById(updateData.getBoardId())) {
            throw new InvalidReferenceException("존재하지 않는 게시판을 참조하고 있습니다.");
        }
        article.setTitle(updateData.getTitle());
        article.setContent(updateData.getContent());
        article.setBoardId(updateData.getBoardId());
        article.setMemberId(updateData.getMemberId());
        return articleRepository.save(article);
    }

    public boolean deleteArticle(Integer id) {
        if (!articleRepository.existById(id)) {
            return false;
        }
        articleRepository.delete(id);
        return true;
    }
}
