package com.example.file.Model;

import javax.validation.constraints.Size;


import lombok.Data;

@Data
public class loginForm {
    @Size(min = 4, max = 20)
    private String id;
    @Size(min = 4, max = 30)
    private String password;

    public Member toMember() {
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        return member;
    }
}


