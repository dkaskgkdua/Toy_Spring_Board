package com.naver.myhome5.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class Board {
	private int BOARD_NUM;
	private String BOARD_NAME;
	private String BOARD_PASS;
	private String BOARD_SUBJECT;
	private String BOARD_CONTENT;
	
	private String BOARD_FILE;
	private int BOARD_RE_REF;
	private int BOARD_RE_LEV;
	private int BOARD_RE_SEQ;
	private int BOARD_READCOUNT;
	private String BOARD_DATE;
	private MultipartFile uploadfile;
	// qna_board_write.jsp에서 name 속성을 확인해야 한다.
	// <input type="file" id ="upfile" name="uploadfile"> 확인
	
	private String BOARD_ORIGINAL;

	
}
