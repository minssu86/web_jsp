package com.minsu.service;

import java.util.List;

import com.minsu.dao.BoardDao;
import com.minsu.dao.BoardLikeDao;
import com.minsu.dao.CommentDao;
import com.minsu.dao.CommentLikeDao;
import com.minsu.dto.BoardRequestDto;
import com.minsu.dto.CommentRequestDto;
import com.minsu.dto.CommentResponseDto;

public class CommentService {
	
	CommentDao commentDao;
	CommentLikeDao commentLikeDao;
	BoardDao boardDao;
	
	public CommentService() {
		this.commentDao = new CommentDao();
		this.commentLikeDao = new CommentLikeDao();
		this.boardDao = new BoardDao();
	}
	
	// 댓글 작성
	public String createComment(int userSeq, CommentRequestDto commentRequestDto) {
		boolean isSuccess = commentDao.save(userSeq, commentRequestDto);
		if(isSuccess){
			if(boardDao.updateCommentCount(commentRequestDto.getBrdSeq(),1)){
				return "성공";
			}
			return "실패2";
		}
		return "실패1";
	}
	
	// 댓글 조회
	public List<CommentResponseDto> getCommentList(int page, int size, int brdSeq, int userSeq) {
		return commentDao.findAll(page-1, size, brdSeq, userSeq);
	}
	
	// 댓글 수정
	public String changeComment(CommentRequestDto commentRequestDto, int userSeq) {
		boolean isSuccess = commentDao.update(userSeq, commentRequestDto);
		if(isSuccess)return "성공";
		return "실패";
	}
	
	// 댓글 삭제
	public String deleteComment(CommentRequestDto commentRequestDto, int userSeq) {
		boolean isSuccess = commentDao.delete(commentRequestDto.getCmtSeq(), userSeq);
		if(isSuccess){
			if(boardDao.updateCommentCount(commentRequestDto.getBrdSeq(),-1)){
				return "성공";
			}
			return "실패2";
		}
		return "실패1";
	}
	
	// 댓글 추천
	public String likeComment(int cmtSeq, int userSeq) {
		boolean isCommentUpdateSuccess;

		boolean isLike = commentLikeDao.find(cmtSeq, userSeq);
		// 추천 이력이 있을때
		if(isLike) {
			isCommentUpdateSuccess = commentDao.updateLikeCount(cmtSeq, -1);
			if(isCommentUpdateSuccess) {
				commentLikeDao.delete(cmtSeq, userSeq);
				return "추천 다운";
			}
		} else {
			// 추천 이력이 없을 때
			isCommentUpdateSuccess = commentDao.updateLikeCount(cmtSeq, 1);
			if(isCommentUpdateSuccess) {
				commentLikeDao.save(cmtSeq, userSeq);
				return "추천 업";
			}
		}
		return "실패";
		
	}
	
}
