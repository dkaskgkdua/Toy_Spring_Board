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
	
	public int getListCount() {
		return sqlSession.selectOne("Boards.count");
	}
	public List<Board> getBoardList(HashMap<String, Integer> map) {
		return sqlSession.selectList("Boards.list", map);
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
	
}
