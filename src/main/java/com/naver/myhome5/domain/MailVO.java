package com.naver.myhome5.domain;

import lombok.Data;

@Data
public class MailVO {
	private String from="dkaskgkdua@naver.com";
	private String to;
	private String subject = "회원 가입을 축하드립니다. - 제목";
	private String content = "회원 가입을 축하드립니다. - 내용";
	
	
}
