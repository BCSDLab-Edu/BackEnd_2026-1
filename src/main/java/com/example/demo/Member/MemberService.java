package com.example.demo.Member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository members;

    public MemberService(MemberRepository membersRepository) {
        this.members = membersRepository;
    }

    @Transactional(readOnly = true)
    public List<Member> getMembers() {
        return members.findAll();
    }

    @Transactional(readOnly = true)
    public Member getMember(Long id) {
        return members.findById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByEmailAddress(String emailAddress) {
        return members.existsByEmailAddress(emailAddress);
    }

    @Transactional
    public void createMember(String nickName, String email, String passWord) {
        Member member = new Member(nickName, email, passWord);
        members.save(member);
    }

    @Transactional
    public void updateMember(Long id, Member updatedMember) {
        Member member = members.findById(id);
        member.modifyMember(
                updatedMember.getNickName(),
                updatedMember.getEmailAddress(),
                updatedMember.getPassWord()
        );
        // Dirty Checking으로 자동 업데이트됨
    }

    @Transactional
    public void deleteMember(Long id) {
        members.delete(id);
    }
}