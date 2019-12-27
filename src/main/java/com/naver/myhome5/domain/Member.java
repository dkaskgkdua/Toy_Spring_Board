package com.naver.myhome5.domain;

import lombok.Data;

/* 데이터베이스, 비지니스 객체, 뷰 객체에서 가져온 값을 저장하거나
 * 데이터베이스, 비지니스 객체, 뷰 객체에 보낼 값을 저장하는 객체를 도메인 객체 또는
 * 도메인 모델이라 한다.
 */
@Data
public class Member {
	private String id;
	// joinForm.jsp에서 비밀번호 name 속성 값을 확인해야 한다.
	private String password;
	private String name;
	private int age;
	private String gender;
	private String email;
	
}
