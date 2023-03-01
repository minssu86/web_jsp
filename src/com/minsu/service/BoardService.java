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
		List<BoardResponseDto> boardResponseDtos = null;
		if(boardRequestDto.getKeyword()==null) {
			boardResponseDtos = boardDao.findAll(boardRequestDto.getPage()-1, boardRequestDto.getSize());
		}else {
			boardResponseDtos = boardDao.findAll(boardRequestDto.getPage()-1, boardRequestDto.getSize(), boardRequestDto.getKeyword());
		}
		int totalBoardCount = boardDao.countBoard();
		if(boardResponseDtos.size()>0)return new ResponseDto<List<BoardResponseDto>>(ResponseStatus.SUCCESS, boardResponseDtos,totalBoardCount);
		return new ResponseDto(ResponseStatus.FAIL);
	}
	
	// 게시글 상세 페이지 조회
	public ResponseDto getBoard(BoardRequestDto boardRequestDto, int userSeq) {
		BoardResponseDto boardResponseDto = boardDao.findById(boardRequestDto.getBrdSeq(), userSeq);
		if(boardResponseDto!=null)return new ResponseDto<BoardResponseDto>(ResponseStatus.SUCCESS, boardResponseDto);
		return new ResponseDto(ResponseStatus.FAIL);
	}
	
	// 게시글 수정
	public ResponseDto changeBoard(BoardRequestDto boardRequestDto, int userSeq) {
		if(boardDao.update(boardRequestDto, userSeq))return new ResponseDto(ResponseStatus.SUCCESS);
		return new ResponseDto(ResponseStatus.FAIL);
	}
	
	// 게시글 삭제
	public ResponseDto deleteBoard(BoardRequestDto boardRequestDto, int userSeq) {
		String cmtArrStr = commentDao.findAllCmt(boardRequestDto.getBrdSeq());
		commentLikeDao.deleteAll(cmtArrStr);
		commentDao.deleteAll(boardRequestDto.getBrdSeq());
		boardLikeDao.deleteAll(boardRequestDto.getBrdSeq());
		boolean isSuccess = boardDao.delete(boardRequestDto.getBrdSeq(), userSeq);
		System.out.println(isSuccess);
		if(isSuccess)return new ResponseDto(ResponseStatus.SUCCESS);
		return new ResponseDto(ResponseStatus.FAIL);
	}
	
	// 게시글 추천
	public ResponseDto likeBoard(int userSeq, BoardRequestDto boardRequestDto) {
		boolean isBoardUpdateSuccess = false;
		BoardResponseDto boardResponseDto = new BoardResponseDto();
		boolean isLike = boardLikeDao.find(boardRequestDto.getBrdSeq(), userSeq);
		// 추천 이력이 있을때
		if(isLike) {
			isBoardUpdateSuccess = boardDao.updateLikeCount(boardRequestDto.getBrdSeq(), -1);
			if(isBoardUpdateSuccess) {
				boardLikeDao.delete(boardRequestDto.getBrdSeq(), userSeq);
				boardResponseDto.setLikeCount(getLikeCount(boardRequestDto.getBrdSeq(), userSeq));
				boardResponseDto.setLiked(false);
				return new ResponseDto<BoardResponseDto>(ResponseStatus.SUCCESS, boardResponseDto);
			}
		} else {
			// 추천 이력이 없을 때 
			isBoardUpdateSuccess = boardDao.updateLikeCount(boardRequestDto.getBrdSeq(), 1);
			if(isBoardUpdateSuccess) {
				boardLikeDao.save(boardRequestDto.getBrdSeq(), userSeq);
				boardResponseDto.setLikeCount(getLikeCount(boardRequestDto.getBrdSeq(), userSeq));
				boardResponseDto.setLiked(true);
				return new ResponseDto<BoardResponseDto>(ResponseStatus.SUCCESS, boardResponseDto);
			}
		}
		return new ResponseDto(ResponseStatus.FAIL);
	}
	
	private int getLikeCount(int brdSeq, int userSeq) {
		return boardLikeDao.count(brdSeq, userSeq);
	}
	
	// 키워드 검색
	public List<BoardResponseDto> searchKeyword(int page, int size, String keyword) {
		return boardDao.findAll(page-1, size, keyword);
	}
}
