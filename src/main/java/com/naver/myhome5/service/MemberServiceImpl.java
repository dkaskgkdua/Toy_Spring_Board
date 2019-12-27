package com.naver.myhome5.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return dao.member_info(id);
	}
	@Override
	public void delete(String id) {
		dao.delete(id);
	}
	@Override
	public int update(Member m) {
		return dao.update(m);
	}
	@Override
	public List<Member> getSearchList(int index, String search_word, int page, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		String field = "";
		switch(index) {
		case 0:  //아이디
			field = "id";
			break;
		case 1:  //이름
			field = "name";
			break;
		case 2:   //이메일
			field = "email";
			break;
		case 3:   //성별
			field = "gender";
			if(search_word.startsWith("남")) {
				search_word = "1";
			} else {
				search_word = "2";
			}
			break;
		}
		System.out.println(field);
		int startrow = (page-1)*limit+1;
		int endrow = startrow+limit-1;
		map.put("field", field);
		map.put("text", search_word);
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getSearchList(map);
	}
	@Override
	public int getSearchListCount(int index, String search_word) {
		Map<String, Object> map = new HashMap<String, Object>();
		String field = "";
		switch(index) {
		case 0:  //아이디
			field = "id";
			break;
		case 1:  //이름
			field = "name";
			break;
		case 2:   //이메일
			field = "email";
			break;
		case 3:   //성별
			field = "gender";
			if(search_word.startsWith("남")) {
				search_word = "1";
			} else {
				search_word = "2";
			}
			break;
		}
		System.out.println(field);

		map.put("field", field);
		map.put("text", search_word);
		return dao.getSearchListCount(map);
	}
}
