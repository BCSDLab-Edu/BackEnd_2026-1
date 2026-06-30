package com.example.demo.Member;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ResponseBody
    @GetMapping("/members")
    public List<Member> getArticles() {
        return memberService.getMembers();
    }

    @ResponseBody
    @GetMapping("/members/{id}")
    public ResponseEntity<?> getArticle(@PathVariable Long id) {
        if (memberService.getMember(id) == null) {
            return ResponseEntity.status(404).body("존재하지 않는 회원입니다.");
        }
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @ResponseBody
    @PostMapping("/members")
    public ResponseEntity<?> createArticle(
            @RequestParam String nickName,
            @RequestParam String email,
            @RequestParam String passWord
    ) {
        if (nickName == null || email == null || passWord == null) {
            return ResponseEntity.status(400).body("조건을 충족하지 못한 요청입니다.");
        }
        memberService.createMember(nickName, email, passWord);
        return ResponseEntity.ok("생성 완료");
    }

    @ResponseBody
    @DeleteMapping("/members/{id}")
    public String deleteArticle(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "삭제 완료";
    }

    @ResponseBody
    @PutMapping("/members/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody Member updatedMember) {

        if (memberService.existsByEmailAddress(updatedMember.getEmailAddress())) {
            return ResponseEntity.status(409).body("이미 존재하는 이메일 입니다.");
        }
        memberService.updateMember(id, updatedMember);
        return ResponseEntity.ok("수정 완료");
    }

}
