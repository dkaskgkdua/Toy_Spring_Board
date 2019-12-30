package com.naver.myhome5.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome5.domain.BoardAjax;
import com.naver.myhome5.domain.Member;
import com.naver.myhome5.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberservice;

	/* @CookieValue(value="saveid", required=false) Cookie readCookie
	 * 이름이 saveid인 쿠키를 Cookie 타입으로 전달 받음
	 * 지정한 이름의 쿠키가 존재하지 않을 수도 있기 때문에 required = false로 설정한다.
	 * required=true 상태에서 지정한 이름을 가진 쿠키가 존재하지 않으면 스프링 MVC는 익셉션을 발생시킨다.
	 * 
	 * */
	// 로그인 폼이동
	@RequestMapping(value = "/login.net", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv,
							  @CookieValue(value="saveid", required=false) Cookie readCookie) {
		if(readCookie != null) {
			mv.addObject("saveid", readCookie.getValue());
		}
		mv.setViewName("member/loginForm");
		return mv;
	}

	@RequestMapping(value = "/member_delete.net", method = RequestMethod.GET)
	public void member_delete(@RequestParam(value = "id") String id, HttpServletResponse response) throws Exception {

		memberservice.delete(id);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제 성공했습니다.');");
		out.println("location.href='member_list.net';");
		out.println("</script>");
		out.close();
	}
	
	@RequestMapping(value ="/my_update.net", method = RequestMethod.POST)
	public void my_update(Member member, HttpServletResponse response) throws Exception {
		int result = memberservice.update(member);

		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (result == 1) {
			out.println("alert('수정성공');");
		} else if (result == -1) {
			out.println("alert('수정 실패');");
		}
		out.println("location.href='my_info.net';");
		out.println("</script>");
		out.close();
	}
	
	@RequestMapping(value = "/my_info.net", method = RequestMethod.GET)
	public Object my_info(HttpSession session, ModelAndView mv) {
		String id = (String) session.getAttribute("id");
		Member m = memberservice.member_info(id);
		mv.setViewName("member/member_info");
		mv.addObject("memberinfo", m);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateProcess.net", method = RequestMethod.POST)
	public void updateProcess(Member member, HttpServletResponse response) throws Exception {
		int result = memberservice.update(member);

		response.setContentType("text/html;charset=utf-8");
		System.out.println(member.getGender());
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (result == 1) {
			out.println("alert('수정성공');");
		} else if (result == -1) {
			out.println("alert('수정 실패');");
		}
		out.println("</script>");
		out.close();
	}
	@ResponseBody
	@RequestMapping(value = "/member_info.net", method = RequestMethod.POST)
	public Object member_info(@RequestParam(value = "id") String id) throws Exception {
		Member mem = memberservice.member_info(id);
		return mem;
	}
	
	

	@RequestMapping(value = "/member_list.net", method = RequestMethod.GET)
	public ModelAndView memberList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
			@RequestParam(value = "search_select", defaultValue = "0", required = false) int search_select,
			@RequestParam(value = "search_word", defaultValue = "", required = false) String search_word,
			ModelAndView mv) throws Exception {
		int listcount = memberservice.getSearchListCount(search_select, search_word); // 총 리스트 수를 받아옴
		// 총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;
		// 현재 페이지에 보여줄 시작 페이지 수
		int startpage = ((page - 1) / 10) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30...)
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		List<Member> memberlist = memberservice.getSearchList(search_select, search_word, page, limit);
		mv.setViewName("member/member_list");
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		mv.addObject("memberlist", memberlist);
		mv.addObject("search_word", search_word);
		mv.addObject("search_select", search_select);
		mv.addObject("limit", limit);

		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/member_listAjax.net", method = { RequestMethod.POST })
	public Object memberListAjax(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
			@RequestParam(value = "search_select", defaultValue = "-1", required = false) int search_select,
			@RequestParam(value = "search_word", defaultValue = "", required = false) String search_word)
			throws Exception {
		int listcount = memberservice.getSearchListCount(search_select, search_word); // 총 리스트 수를 받아옴
		// 총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;
		// 현재 페이지에 보여줄 시작 페이지 수
		int startpage = ((page - 1) / 10) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30...)
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		List<Member> memberlist = memberservice.getSearchList(search_select, search_word, page, limit);
		// BoardAjax 이용하기
		BoardAjax ba = new BoardAjax();
		ba.setPage(page);
		ba.setMaxpage(maxpage);
		ba.setStartpage(startpage);
		ba.setEndpage(endpage);
		ba.setListcount(listcount);
		ba.setMemberlist(memberlist);
		ba.setSearch_select(search_select);
		ba.setSearch_word(search_word);
		ba.setLimit(limit);

		// Map 이용하기
		/*
		 * Map<String, Object> ba = new HashMap<String, Object>(); ba.put("page",page);
		 * ba.put("maxpage", maxpage); ba.put("startpage", startpage); ba.put("endpage",
		 * endpage); ba.put("listcount", listcount); ba.put("boardlist", boardlist);
		 * ba.put("limit",limit);
		 */

		return ba;
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
								@RequestParam(value="remember", defaultValue="") String remember,
								HttpServletRequest request,	HttpServletResponse response, HttpSession session) throws Exception {

		int result = memberservice.isId(id, password);
		System.out.println("결과는 " + result);

		if (result == 1) {
			session.setAttribute("id", id);
			Cookie savecookie = new Cookie("saveid", id);
			if(!remember.equals("") ) {
				savecookie.setMaxAge(60*60);
				System.out.println("쿠키저장 : 60*60");
			} else {
				System.out.println("쿠키 저장 : 0");
				savecookie.setMaxAge(0);
			}
			response.addCookie(savecookie);
			
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
	@RequestMapping(value = "/logout.net", method = RequestMethod.GET)
	public String logout(HttpServletResponse response, HttpSession session) throws Exception {
		session.invalidate();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('로그아웃 되었습니다.');");
		out.println("location.href='login.net';");
		out.println("</script>");
		out.close();
		return null;
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
