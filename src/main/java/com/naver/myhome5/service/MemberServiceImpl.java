package com.naver.myhome5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.myhome5.dao.MemberDAO;
import com.naver.myhome5.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO dao;
	
	@Override
	public int insert(Member m) {
		return dao.insert(m);
	}
	@Override
	public int isId(String id) {
		Member rmember = dao.isId(id);
		return rmember==null ? -1 : 1;
	}	
	@Override
	public int isId(String id, String password) {
		Member rmember = dao.isId(id);
		int result=-1; //아이디가 존재하지 않은 경우 rmember가 null인 경우
		if(rmember!= null) { //아이디 존재
			if(rmember.getPassword().equals(password)) {
				result = 1; // 아이디와 비밀번호 일치
			} else {
				result = 0; //아이디는 존재하지만 비밀번호가 일치하지 않는 경우
			}
		}
		return result;
	}
	
	

	@Override
	public Member member_info(String id) {
		return new Member();
	}
	@Override
	public void delete(String id) {
		
	}
	@Override
	public int update(Member m) {
		return 1;
	}
	@Override
	public List<Member> getSearchList(int index, String search_word, int page, int limit) {
		return new ArrayList<Member>();
	}
	@Override
	public int getSearchListCount(int index, String search_word) {
		return 1;
	}
}
