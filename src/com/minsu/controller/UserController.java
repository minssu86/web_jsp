package com.minsu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minsu.dto.ResponseDto;
import com.minsu.dto.UserRequestDto;
import com.minsu.service.UserService;

public class UserController extends HttpServlet {

	private static final String untilVersion = "/web_jsp/v1/user/";
	private static final String signup = untilVersion + "checked-email"; // get
	private static final String requestEmail = untilVersion + "signup"; // post
	private static final String checkDuplicatedEmail = untilVersion + "duplication/email"; // get
	private static final String checkDuplicatedNickname = untilVersion + "duplication/nickname"; // get
	private static final String checkDuplicatedUserId = untilVersion + "duplication/id"; // get
	private static final String login = untilVersion + "login"; // post
	private static final String findUserId = untilVersion + "id"; //get
	private static final String getIdCheckCode = untilVersion + "id-check"; // get
	private static final String getPasswordCheckCode = untilVersion + "password-check"; // get
	private static final String checkPasswordCode = untilVersion + "password-check"; // post
	private static final String changePassword = untilVersion + "password"; // put
	
	private final UserService userService;
	public UserController() {
		this.userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//this.getInitParameter(name)
		ServletContext context = req.getServletContext();
		String url = context.getInitParameter("url");
		String user = context.getInitParameter("user");
		String password = context.getInitParameter("password");
		
		String uri = req.getRequestURI();
		System.out.println("get : " + uri);
		UserRequestDto userRequestDto = setRequestDto(req);
		ResponseDto responseDto = new ResponseDto();
		try {
			switch (uri) {
			case signup: responseDto = userService.signup(userRequestDto);
			break;
			case checkDuplicatedEmail: responseDto = userService.checkDuplicatedEmail(userRequestDto);
				break;
			case checkDuplicatedNickname: responseDto = userService.checkDuplicatedNickname(userRequestDto); 
				break;
			case checkDuplicatedUserId: responseDto = userService.checkDuplicatedUserId(userRequestDto); 
				break;
			case findUserId:
				break;
			case getIdCheckCode:
				break;
			
			default:
				break;
			}
			responseData(resp, responseDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void responseData(HttpServletResponse resp, ResponseDto responseDto) throws IOException {
		resp.setContentType("application/json");		
		resp.setHeader("Access-Control-Allow", "*");
//		resp.setStatus();
		System.out.println("responseData : "+responseDto.getData());
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		System.out.println("post : " + uri);
		UserRequestDto userRequestDto = setRequestDto(req);
		ResponseDto responseDto = new ResponseDto();
		switch (uri) {
		case requestEmail: responseDto = userService.requestEmail(userRequestDto);
			break;
		case login:
			break;
		case checkPasswordCode:
			break;
		
		default:
			break;
		}
		responseData(resp, responseDto);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		UserRequestDto userRequestDto = new UserRequestDto();
		System.out.println(uri);
		Object object = new Object();
		switch (uri) {
		case getPasswordCheckCode:
			break;
		
		default:
			break;
		}
	}
	
	protected void setResp(HttpServletResponse resp) {
		resp.setContentType("application/json");
	}
	
	protected UserRequestDto setRequestDto(HttpServletRequest req) {
		UserRequestDto userRequestDto = new UserRequestDto();
		if(req.getParameter("userSeq")!=null)userRequestDto.setUserSeq(Integer.parseInt(req.getParameter("userSeq")));
		if(req.getParameter("id")!=null)userRequestDto.setUserId(req.getParameter("id"));
		if(req.getParameter("password")!=null)userRequestDto.setUserPassword(req.getParameter("password"));
		if(req.getParameter("name")!=null)userRequestDto.setUserName(req.getParameter("name"));
		if(req.getParameter("nickname")!=null)userRequestDto.setUserNickname(req.getParameter("nickname"));
		if(req.getParameter("email")!=null)userRequestDto.setUserEmail(req.getParameter("email"));
		if(req.getParameter("profile")!=null)userRequestDto.setUserProfile(req.getParameter("profile"));
		if(req.getParameter("checkcode")!=null)userRequestDto.setCheckCode(req.getParameter("checkcode"));
		return userRequestDto;
	}
	
}
