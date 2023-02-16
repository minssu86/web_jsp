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
import com.minsu.dto.ResponseDto;
import com.minsu.dto.ResponseStatus;
import com.minsu.dto.UserResponseDto;
import com.minsu.service.BoardService;

public class BoardController extends HttpServlet {

	private static final String UNTIL_VERSION = "/web_jsp/v1/board";
	private static final String GET_DETAIL = "/web_jsp/v1/board/*";
//	private static final String DELETE = "/web_jsp/v1/board";
	public static Map<String, UserResponseDto> sessionStore = new HashMap<>();
	
	private final BoardService boardService;
	public BoardController() {
		this.boardService = new BoardService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		BoardRequestDto boardRequestDto = setRequestDto(req);
		ResponseDto responseDto = new ResponseDto();
		System.out.println(uri);
		try {
			switch (uri) {
			case UNTIL_VERSION: 
				responseDto = boardService.getBoardList(boardRequestDto);
			break;
			case GET_DETAIL: 
				System.out.println("도착");
//				responseDto = boardService.getBoardList(boardRequestDto);
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
		ResponseDto<?> responseDto = new ResponseDto();
		switch (uri) {
		case UNTIL_VERSION: 
			int userSeq = userInfo(req);
			if(userSeq>0) {
				responseDto = boardService.createBoard(userSeq, boardRequestDto);
			} else {
				responseDto.setStatus(ResponseStatus.FAIL);
			}
		System.out.println("1");
		break;
		default:
			break;
		}
		responseData(resp, responseDto);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		BoardRequestDto boardRequestDto = setRequestDto(req);
		ResponseDto responseDto = new ResponseDto();
		System.out.println(uri);
		try {
			switch (uri) {
			case UNTIL_VERSION: 
				int userSeq = userInfo(req);
				responseDto = boardService.deleteBoard(boardRequestDto, userSeq);
			break;
						
			default:
				break;
			}
			responseData(resp, responseDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Override
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String uri = req.getRequestURI();
//		UserRequestDto userRequestDto = new UserRequestDto();
//		Object object = new Object();
//		switch (uri) {
//		case getPasswordCheckCode:
//			break;
//		
//		default:
//			break;
//		}
//	}
	
	

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
		resp.setHeader("Access-Control-Allow", "*");
//		resp.setStatus();
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
		System.out.println(boardRequestDto.toString());
		return boardRequestDto;
	}
	
	
}
