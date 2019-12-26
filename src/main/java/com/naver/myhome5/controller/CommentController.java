package com.naver.myhome5.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naver.myhome5.domain.Comment;
import com.naver.myhome5.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping(value = "CommentUpdate.bo")
	public void CommentUpdate(Comment co, HttpServletResponse response) throws Exception {
		System.out.println(" dd : "+ co.getContent());
		int ok = commentService.commentsUpdate(co);
		response.getWriter().print(ok);
	}
	
	@PostMapping(value = "CommentDelete.bo")
	public void CommentDelete(@RequestParam("num") int num, HttpServletResponse response) throws Exception {
		System.out.println(" num : "+ num);
		int ok = commentService.commentsDelete(num);
		response.getWriter().print(ok);
	}
	
	@PostMapping(value = "CommentAdd.bo")
	public void CommentAdd(Comment co, HttpServletResponse response) throws Exception {
		int ok = commentService.commentsInsert(co);
		response.getWriter().print(ok);
	}
	
	/*  @ResponseBody와 jackson을 이용하여 JSON 사용하기
	 * 
	 *  @ResponseBody란?
	 *  메서드에 @ResponseBody Annotation이 되어 있으면 return 되는 값은 View를 통해서
	 *  출력되는 것이 아니라 HTTP ResponseBody에 직접쓰여지게 된다.
	 *  예) HTTP 응답 프로토콜의 구조 HTTP/1.1
	 *  Message Header
	 *  200OK => Start Line Content-Type:text/html =>Message Header Connect
	 *  close Server : Apache Tomcat.. Last-Modified : Mon,...
	 *  
	 *  Message Body
	 *  <html> <head><title>Hello JSP</title></head> <body>Hello JSP!</body></html>
	 *  =>
	 *  
	 *  응답 결과를 HTML이 아닌 JSON으로 변환하여 Message Body에 저장하려면 스프링에서 제공하는 변환기(Converter)를 사용해야 한다.
	 *  이 변환기를 이용해서 자바 객체를 다양한 타입으로 변환하여 HTTp ResponseBody에 설정할 수 있다.
	 *  스프링 설정 파일에 <mvc:annotation-driven>을 설정하면 변환기가 생성된다.
	 *  이 중에서 자바 객체를 JSON 응답 바디로 변환할 때는
	 *  MappingJackson2HttpMessageConverter를 사용합니다.
	 *  
	 *  @ResponseBody를 이용해서 각 메서드의 실행 결과는 JSON으로 변환되어 HTTP 응답 BODY에 설정한다.
	 */
	@ResponseBody
	@PostMapping(value = "CommentList.bo")
	public List<Comment> CommentList(@RequestParam("BOARD_RE_REF") int BOARD_RE_REF) {
		List<Comment> list = commentService.getCommentList(BOARD_RE_REF);
		System.out.println(list);
		return list;
	}
}
