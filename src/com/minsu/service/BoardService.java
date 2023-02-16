package com.minsu.service;

import java.util.List;

import com.minsu.dao.BoardDao;
import com.minsu.dao.BoardLikeDao;
import com.minsu.dao.CommentDao;
import com.minsu.dao.CommentLikeDao;
import com.minsu.dto.BoardRequestDto;
import com.minsu.dto.BoardResponseDto;
import com.minsu.dto.ResponseDto;
import com.minsu.dto.ResponseStatus;

public class BoardService {

	private final BoardDao boardDao;
	private final BoardLikeDao boardLikeDao;
	private final CommentDao commentDao;
	private final CommentLikeDao commentLikeDao;
	
	public BoardService() {
		this.boardDao = new BoardDao();
		this.boardLikeDao = new BoardLikeDao();
		this.commentDao = new CommentDao();
		this.commentLikeDao = new CommentLikeDao();
	}
	
	// 게시글 작성
	public ResponseDto<?> createBoard(int userSeq, BoardRequestDto boardRequestDto) {
		boolean isSuccess = boardDao.save(boardRequestDto,userSeq);
		if(isSuccess)return new ResponseDto(ResponseStatus.SUCCESS);
		return new ResponseDto(ResponseStatus.FAIL);
	}
	
	// 게시글 리스트 조회
	public ResponseDto getBoardList(BoardRequestDto boardRequestDto) {
		List<BoardResponseDto> boardResponseDtos = boardDao.findAll(boardRequestDto.getPage()-1, boardRequestDto.getSize());
		if(boardResponseDtos.size()>0)return new ResponseDto<List<BoardResponseDto>>(ResponseStatus.SUCCESS, boardResponseDtos);
		return new ResponseDto(ResponseStatus.FAIL);
	}
	
	// 게시글 상세 페이지 조회
	public BoardResponseDto getBoard(int brdSeq, int userSeq) {
		return boardDao.findById(brdSeq, userSeq);
	}
	
	// 게시글 수정
	public String changeBoard(BoardRequestDto boardRequestDto, int brdSeq, int userSeq) {
		boolean isSuccess = boardDao.update(brdSeq, userSeq, boardRequestDto);
		if(isSuccess)return "성공";
		return "실패";
	}
	
	// 게시글 삭제
	public ResponseDto deleteBoard(BoardRequestDto boardRequestDto, int userSeq) {
		String cmtArrStr = commentDao.findAllCmt(boardRequestDto.getBrdSeq());
		System.out.println("delete service : " + cmtArrStr);
		commentLikeDao.deleteAll(cmtArrStr);
		commentDao.deleteAll(boardRequestDto.getBrdSeq());
		boolean isSuccess = boardDao.delete(boardRequestDto.getBrdSeq(), userSeq);
		if(isSuccess)return new ResponseDto(ResponseStatus.SUCCESS);
		return new ResponseDto(ResponseStatus.FAIL);
	}
	
	// 게시글 추천
	public String likeBoard(int brdSeq, int userSeq) {
		boolean isBoardLikeSuccess = false;
		boolean isBoardUpdateSuccess = false;
		
		boolean isLike = boardLikeDao.find(brdSeq, userSeq);
		// 추천 이력이 있을때
		if(isLike) {
			isBoardUpdateSuccess = boardDao.updateLikeCount(brdSeq, -1);
			if(isBoardUpdateSuccess) {
				boardLikeDao.delete(brdSeq, userSeq);
				return "추천 다운";
			}
		} else {
			// 추천 이력이 없을 때 
			isBoardUpdateSuccess = boardDao.updateLikeCount(brdSeq, 1);
			if(isBoardUpdateSuccess) {
				boardLikeDao.save(brdSeq, userSeq);
				return "추천 업";
			}
		}
		return "실패";
	}
	
	// 키워드 검색
	public List<BoardResponseDto> searchKeyword(int page, int size, String keyword) {
		return boardDao.findAll(page-1, size, keyword);
	}
}
