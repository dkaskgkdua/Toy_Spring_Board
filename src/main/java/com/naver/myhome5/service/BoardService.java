package com.naver.myhome5.service;

import java.util.List;

import com.naver.myhome5.domain.Board;

public interface BoardService {
	// 글 목록
	public List<Board> getBoardList(int page, int limit, int search_select, String search_text);
	// 글 갯수
	public int getListCount(int search_select, String search_text);
	// 글 내용
	public Board getDetail(int num);
	// 글 답변
	public int boardReply(Board board);
	// 글 수정
	public int boardModify(Board modifyboard);
	// 글 삭제
	public int boardDelete(int num);
	// 조회수 업데이트
	public int setReadCountUpdate(int num);
	//글쓴이인지 확인
	public boolean isBoardWriter(int num, String pass);
	// 글 등록하기
	public void insertBoard(Board board);
	// 시퀀스 수정
	public int boardReplyUpdate(Board board);
	// 변경, 삭제 파일 목록에 추가
	public void insert_deleteFile(String before_file);
	// 수정, 삭제 했던 파일 목록에 있는 것 삭제
	public void delete_file(String saveFolder);
}
