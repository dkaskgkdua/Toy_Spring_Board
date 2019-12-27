package com.naver.myhome5.dao;

import java.util.List;
import java.util.Map;

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
	 * public int isId(String id, String password) { return
	 * sqlSession.selectOne("Members.idcheck", id); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public int update(Member m) {
		return sqlSession.update("Members.update", m);
	}
	public Member member_info(String id) {
		return sqlSession.selectOne("Members.select", id);
	}
	public void delete(String id) {
		sqlSession.delete("Members.delete", id);
	}

	public List<Member> getSearchList(Map<String, Object> map) {
		return sqlSession.selectList("Members.list", map);
	}

	public int getSearchListCount(Map<String, Object> map) {
		return sqlSession.selectOne("Members.count", map);
	}
}
