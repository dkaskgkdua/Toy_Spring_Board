package com.naver.myhome5.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome5.domain.Member;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insert(Member m) {
		return sqlSession.insert("Members.insert", m);
	}
	public Member isId(String id) {
		return sqlSession.selectOne("Members.idcheck", id);
	}
	/*
	public int isId(String id, String password) {
		return sqlSession.selectOne("Members.idcheck", id);
	}
	
	public Member member_info(String id) {
		
	}
	
	public void delete(String id) {
		
	}
	
	public int update(Member m) {
		
	}
	
	public List<Member> getSearchList(int index, String search_word, int page, int limit) {
		
	}
	
	public int getSearchListCount(int index, String search_word) {
		
	}*/
}
