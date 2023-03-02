package com.minsu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minsu.dto.BoardRequestDto;
import com.minsu.dto.BoardResponseDto;
import com.minsu.dto.ResponseDto;
import com.minsu.dto.ResponseStatus;
import com.minsu.dto.UserResponseDto;
import com.minsu.service.BoardService;

public class BoardController extends HttpServlet {

	private static final String UNTIL_VERSION = "/web_jsp/v1/board";
	private static final String GET_DETAIL = "/web_jsp/v1/board/detail";
	private static final String IS_LIKE = "/web_jsp/v1/board/like";
	private static final String EDIT = "/web_jsp/v1/board/edit";
	public static Map<String, UserResponseDto> sessionStore = new HashMap<>();
	private ResponseDto responseDto = new ResponseDto();
	private final BoardService boardService;
	public BoardController() {
		this.boardService = new BoardService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		BoardRequestDto boardRequestDto = setRequestDto(req);
		try {
			switch (uri) {
			case UNTIL_VERSION:  // 게시글 리스트
				responseDto = boardService.getBoardList(boardRequestDto);
				break;
			case GET_DETAIL:  // 게시글 상세페이지
				responseDto = boardService.getBoard(boardRequestDto, userInfo(req));
				break;
			default:
				break;
			}
			responseData(resp, responseDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		BoardRequestDto boardRequestDto = setRequestDto(req);
		int userSeq = -1;
		try {
			switch (uri) {
			case UNTIL_VERSION: // 게시글 작성
				userSeq = userInfo(req);
				userSeq=18;
				if(userSeq>0) {
					responseDto = boardService.createBoard(userSeq, boardRequestDto);
				} else {
					responseDto.setStatus(ResponseStatus.FAIL);
				}
				break;
			case IS_LIKE:  // 게시글 추천
				userSeq = userInfo(req);
				userSeq=18;
				if(userSeq>0) {
					responseDto = boardService.likeBoard(userSeq, boardRequestDto);
				} else {
					responseDto.setStatus(ResponseStatus.FAIL);
				}
				break;
			case EDIT:  // 게시글 수정
				userSeq = userInfo(req);
				userSeq=18;
				if(userSeq>0) {
					responseDto = boardService.changeBoard(boardRequestDto, userSeq);
				} else {
					responseDto.setStatus(ResponseStatus.FAIL);
				}
				break;
			default:
				break;
			}
		responseData(resp, responseDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		BoardRequestDto boardRequestDto = setRequestDto(req);
		int userSeq = -1;
		try {
			switch (uri) {
			case UNTIL_VERSION:  // 게시글 삭제
				userSeq = userInfo(req);
				userSeq=18;
				responseDto = boardService.deleteBoard(boardRequestDto, userInfo(req));
				break;
			default:
				break;
			}
			responseData(resp, responseDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int userInfo(HttpServletRequest req) {
		String userSesseion = getUserSession(req);
		UserResponseDto userResponseDto = UserController.sessionStore.get(userSesseion);
		if(userResponseDto==null) return -1;
		return userResponseDto.getUserSeq();
	}

	// 쿠키에서 세션 아이디 추출
	private String getUserSession(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userSession")) {
                    return cookie.getValue();
                }
            }
        } 
		return null;
	}
	
	// 응답 데이터 json 변환
	private void responseData(HttpServletResponse resp, ResponseDto responseDto) throws IOException {
		resp.setContentType("application/json; charset=utf-8");	
		resp.setHeader("Access-Control-Allow-Origin", "*");	
		
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		resp.setHeader("Access-Control-Max-Age", "3600");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
		
		
		try(PrintWriter out = resp.getWriter();){
			// 상태 코드 반환
			out.print("{\"status\":");
			out.print("\""+ responseDto.getStatus().toString() +"\"");
			out.print(",");
			// 데이터 반환
			out.print("\"data\":");
			try {
				out.append(responseDto.getData().toString());
			}catch (Exception e) {
				out.print("[]");			
			}
			out.print(",");
			out.print("\"totalBoardCount\":");
			try {
				out.print(+ responseDto.getTotalBoardCount());
			}catch (Exception e) {
				out.print("0");
			}
			out.print("}");
		}
	}
	
	// 요청 데이터 dto 매핑
	protected BoardRequestDto setRequestDto(HttpServletRequest req) {
		BoardRequestDto boardRequestDto = new BoardRequestDto();
		if(req.getParameter("brdSeq")!=null)boardRequestDto.setBrdSeq(Integer.parseInt(req.getParameter("brdSeq")));
		if(req.getParameter("title")!=null)boardRequestDto.setBrdTitle(req.getParameter("title"));
		if(req.getParameter("content")!=null)boardRequestDto.setBrdContent(req.getParameter("content"));
		if(req.getParameter("page")!=null)boardRequestDto.setPage(Integer.parseInt(req.getParameter("page")));
		if(req.getParameter("size")!=null)boardRequestDto.setSize(Integer.parseInt(req.getParameter("size")));
		if(req.getParameter("keyword")!=null)boardRequestDto.setKeyword(req.getParameter("keyword"));
		return boardRequestDto;
	}
}
