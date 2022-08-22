package com.example.file.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.file.Model.Member;
import com.example.file.repository.MemberMapper;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberMapper memberMapper;

	public void saveMember(Member member) {
		
		memberMapper.saveMember(member);
	}

    public Member findMember(Member member) {
        return memberMapper.findMemberByIdAndPassword(member);
    }



}
