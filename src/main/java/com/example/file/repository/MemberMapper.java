package com.example.file.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.file.Model.Member;
@Mapper
public interface MemberMapper {

	public void saveMember(Member member);

	public Member findMemberByIdAndPassword(Member member);


}
