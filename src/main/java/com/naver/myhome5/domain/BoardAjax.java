package com.naver.myhome5.domain;

import java.util.List;

import lombok.Data;
@Data
public class BoardAjax {
	private int page;
	private int maxpage;
	private int startpage;
	private int endpage;
	private int listcount;
	private int limit;
	private int search_select;
	private String search_word;
	private List<Board> boardlist;
	private List<Member> memberlist;
	
}
