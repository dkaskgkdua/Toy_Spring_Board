package com.naver.myhome5.domain;

import lombok.Data;
@Data
public class Comment {
	private int num;
	private String id;
	private String content;
	private String reg_date;
	private int BOARD_RE_REF;
}
