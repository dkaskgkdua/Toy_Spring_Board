package com.naver.myhome5.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome5.domain.Comment;

@Repository
public class CommentDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int getListCount(int num) {
		return sqlSession.selectOne("Comments.count", num);
	}
	public int commentsInsert(Comment comment) {
		return sqlSession.insert("Comments.insert", comment);
	}
	public List<Comment> getCommentList(int BOARD_RE_REF) {
		return sqlSession.selectList("Comments.list", BOARD_RE_REF);
	}
	
	public int commentDelete(int num) {
		return sqlSession.delete("Comments.delete", num);
	}
	public int commentsUpdate(Comment co) {
		return sqlSession.update("Comments.update", co);
	}
}
