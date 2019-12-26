package com.naver.myhome5.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.naver.myhome5.domain.Member;
import com.naver.myhome5.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberservice;
	
	
	// 로그인 폼이동
	@RequestMapping(value = "/login.net", method = RequestMethod.GET)
	public String login() {
		return "member/loginForm";
	}
	
	
	// 회원가입 폼 이동
	@RequestMapping(value = "/join.net", method = RequestMethod.GET)
	public String join() {
		return "member/joinForm";
	}

	@RequestMapping(value = "/idcheck.net", method = RequestMethod.GET)
	public void idcheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		int result = memberservice.isId(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

	@RequestMapping(value = "/loginProcess.net", method = RequestMethod.POST)
	public String loginProcess(@RequestParam("id") String id, @RequestParam("password") String password,
			HttpServletResponse response, HttpSession session) throws Exception {

		int result = memberservice.isId(id, password);
		System.out.println("결과는 " + result);

		if (result == 1) {
			session.setAttribute("id", id);
			return "redirect:BoardList.bo";
		} else {
			String message = "비밀번호가 일치하지 않습니다.";
			if (result == -1) {
				message = "아이디가 존재하지 않습니다.";
			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("location.href='login.net';");
			out.println("</script>");
			out.close();
			return null;
		}
	}

	@RequestMapping(value = "/joinProcess.net", method = RequestMethod.GET)
	public void joinProcess(Member member, HttpServletResponse response) throws Exception {
		/*
		 * Member m = new Member(); m.setId(id); m.setPassword(password);
		 * m.setName(name); m.setAge(age); m.setGender(gender); m.setEmail(email);
		 */
		int result = memberservice.insert(member);

		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (result == 1) {
			out.println("alert('가입성공');");
			out.println("location.href='login.net';");
		} else if (result == -1) {
			out.println("alert('아이디 중복');");
			out.println("history.go(-1);");
		}
		out.println("</script>");
		out.close();

	}

}
