package com.naver.myhome5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.myhome5.dao.CommentDAO;
import com.naver.myhome5.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDAO dao;

	@Override
	public int getListCount(int BOARD_RE_REF) {
		return dao.getListCount(BOARD_RE_REF);
	}

	@Override
	public List<Comment> getCommentList(int BOARD_RE_REF) {
		
		return dao.getCommentList(BOARD_RE_REF);
	}

	@Override
	public int commentsInsert(Comment c) {
		return dao.commentsInsert(c);
	}

	@Override
	public int commentsDelete(int num) {
		return dao.commentDelete(num);
	}
	
	@Override
	public int commentsUpdate(Comment co) {
		return dao.commentsUpdate(co);
	}
}
