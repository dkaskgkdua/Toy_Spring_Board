package com.naver.myhome5.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.myhome5.dao.BoardDAO;
import com.naver.myhome5.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO dao;
	
	@Override
	public int getListCount(int search_select, String search_text) {
		Map<String, Object> map = new HashMap<String, Object>();
		String field = "";
		String field2 = "";
		switch(search_select) {
		case 0:  //작성자
			field = "BOARD_NAME";
			break;
		case 1:  //제목
			field = "BOARD_SUBJECT";
			break;
		case 2:   //내용
			field = "BOARD_CONTENT";
			break;
		case 3:   //제목+내용
			field = "BOARD_SUBJECT";
			field2 = "BOARD_CONTENT";
			break;
		}
		System.out.println(field);
		map.put("field", field);
		map.put("field2", field2);
		map.put("text", search_text);
		return dao.getListCount(map);
	}
	@Override
	public List<Board> getBoardList(int page, int limit, int search_select, String search_text) {
		Map<String, Object> map = new HashMap<String, Object>();
		String field = "";
		String field2 = "";
		switch(search_select) {
		case 0:  //작성자
			field = "BOARD_NAME";
			break;
		case 1:  //제목
			field = "BOARD_SUBJECT";
			break;
		case 2:   //내용
			field = "BOARD_CONTENT";
			break;
		case 3:   //제목+내용
			field = "BOARD_SUBJECT";
			field2 = "BOARD_CONTENT";
			break;
		}
		
		System.out.println(field);
		int startrow = (page-1)*limit+1;
		int endrow = startrow+limit-1;
		map.put("field2", field2);
		map.put("field",field);
		map.put("text", search_text);
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getBoardList(map);
		
	}
	
	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}
	
	@Override
	public Board getDetail(int num) {
		if(setReadCountUpdate(num)!=1)
			return null;
		return dao.getDetail(num);
	}
	
	@Override
	public int boardReply(Board board) {
		boardReplyUpdate(board);
		board.setBOARD_RE_LEV(board.getBOARD_RE_LEV()+1);
		board.setBOARD_RE_SEQ(board.getBOARD_RE_SEQ()+1);
		return dao.boardReply(board);
	}
	@Override
	public int boardModify(Board modifyboard) {
		return dao.boardModify(modifyboard);
	}
	@Override
	public int boardDelete(int num) {
		int result =0;
		Board board = dao.getDetail(num);
		if(board != null) {
			result = dao.boardDelete(board);
		}
		return result;
	}
	@Override
	public boolean isBoardWriter(int num, String pass) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		Board result = dao.isBoardWriter(map);
		if(result == null)
			return false;
		else
			return true;
	}
	@Override
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}
	@Override
	public int boardReplyUpdate(Board board) {
		return dao.boardReplyUpdate(board);
	}
}
	
