package com.example.demo.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nickName;

    @Column(name = "email")
    private String emailAddress;

    @Column(name = "password")
    private String passWord;

    public Member(String nickName, String email, String passWord) {
        this.nickName = nickName;
        this.emailAddress = email;
        this.passWord = passWord;
    }

    public void modifyMember(String modifiedNickName,
                             String modifiedEmail,
                             String modifiedPassWord) {
        this.nickName = modifiedNickName;
        this.emailAddress = modifiedEmail;
        this.passWord = modifiedPassWord;
    }
}