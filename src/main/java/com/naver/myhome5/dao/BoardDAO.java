package com.naver.myhome5.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome5.domain.Board;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int getListCount(Map<String, Object> map) {
		if(map.get("field2").equals("")) {
			return sqlSession.selectOne("Boards.count", map);
		} else { // 제목+내용일 경우
			return sqlSession.selectOne("Boards.countOR", map);
		}
	}
	public List<Board> getBoardList(Map<String, Object> map) {
		if(map.get("field2").equals("")) {
			return sqlSession.selectList("Boards.list", map);
		} else { // 제목+내용일 경우
			return sqlSession.selectList("Boards.listOR", map);
		}
		
	}
	public int boardDelete(Board board) {
		return sqlSession.delete("Boards.delete", board);
	}
	
	public int setReadCountUpdate(int num) {
		return sqlSession.update("Boards.ReadCountUpdate", num);
	}
	public int boardReply(Board board) {
		return sqlSession.insert("Boards.reply_insert", board);
	}
	public int boardModify(Board modifyboard) {
		return sqlSession.update("Boards.modify", modifyboard);
	}
	public Board getDetail(int num) {
		return sqlSession.selectOne("Boards.Detail", num);
	}
	
	public void insertBoard(Board board) {
		sqlSession.insert("Boards.insert", board);
	}
	public int boardReplyUpdate(Board board) {
		return sqlSession.update("Boards.reply_update", board);
	}
	public Board isBoardWriter(Map map) {
		return sqlSession.selectOne("Boards.BoardWriter", map);
	}
	public void insert_deleteFile(String before_file) {
		sqlSession.insert("Boards.insert_deleteFile",before_file);
	}
	public List<String> select_delete_file() {
		List<String> list = sqlSession.selectList("Boards.delete_file");
		sqlSession.delete("Boards.delete_delete_file");
		return list;
	}
	
	
}
