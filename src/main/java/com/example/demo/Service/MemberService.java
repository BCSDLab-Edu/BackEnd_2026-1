package com.example.demo.Service;

import com.example.demo.Exception.DeleteRestrictionException;
import com.example.demo.Exception.DuplicateEmailException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Member;
import com.example.demo.Repository.ArticleRepository;
import com.example.demo.Repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private int idCount = 0;

    public MemberService(MemberRepository memberRepository, ArticleRepository articleRepository) {
        this.memberRepository = memberRepository;
        this.articleRepository = articleRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Integer id) {
        Member member = memberRepository.findById(id);
        if (member == null) {
            throw new ResourceNotFoundException("해당 ID의 사용자를 찾을 수 없습니다.");
        }
        return member;
    }

    public Member createMember(Member member) {
        idCount++;
        member.setId(idCount);
        return memberRepository.save(member);
    }

    public Member updateMember(Integer id, Member updateData) {
        Member member = memberRepository.findById(id);
        if (member == null) {
            throw new ResourceNotFoundException("사용자를 찾을 수 없습니다.");
        }
        boolean emailExists = memberRepository.findAll().stream().anyMatch(m ->
                m.getEmail().equals(updateData.getEmail()) && !m.getId().equals(id));
        if (emailExists) {
            throw new DuplicateEmailException("이미 사용 중인 이메일 입니다.");
        }
        member.setName(updateData.getName());
        member.setEmail(updateData.getEmail());
        return memberRepository.save(member);
    }

    public boolean deleteMember(Integer id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("사용자를 찾을 수 없습니다.");
        }
        boolean hasArticles = articleRepository.findAll().stream().anyMatch(article ->
                id.equals(article.getMemberId()));
        if (hasArticles) {
            throw new DeleteRestrictionException("사용자가 작성한 게시물이 존재하여 삭제할 수 없습니다.");
        }

        memberRepository.deleteById(id);
        return hasArticles;
    }
}
