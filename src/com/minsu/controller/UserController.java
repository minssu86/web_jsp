package com.minsu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.minsu.dto.ResponseDto;
import com.minsu.dto.ResponseStatus;
import com.minsu.dto.UserRequestDto;
import com.minsu.dto.UserResponseDto;
import com.minsu.service.UserService;

public class UserController extends HttpServlet {

	private static final String UNTIL_VERSION = "/web_jsp/v1/user/";
	private static final String LOGOUT = UNTIL_VERSION + "logout"; // get
	private static final String SIGNUP = UNTIL_VERSION + "checked-email"; // get
	private static final String REQUEST_EMAIL = UNTIL_VERSION + "signup"; // post
	private static final String CHECK_DUPLICATED_EMAIL = UNTIL_VERSION + "duplication/email"; // get
	private static final String CHECK_DUPLICATED_NICKNAME = UNTIL_VERSION + "duplication/nickname"; // get
	private static final String CHECK_DUPLICATED_USER_ID = UNTIL_VERSION + "duplication/id"; // get
	private static final String LOGIN = UNTIL_VERSION + "login"; // post
	private static final String GET_ID_CHECK_COEDE = UNTIL_VERSION + "id-check"; // get
	private static final String GET_PASSWORD_CHECK_CODE = UNTIL_VERSION + "password-check"; // get
	private static final String CHECK_PASSWORD_CODE = UNTIL_VERSION + "password-check"; // post
	private static final String CHANGE_PASSWORD = UNTIL_VERSION + "password"; // put
	
	public static Map<String, UserResponseDto> sessionStore = new HashMap<>();

	
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
		UserRequestDto userRequestDto = setRequestDto(req);
		ResponseDto responseDto = new ResponseDto();
		try {
			switch (uri) {
			case LOGOUT: logoutProcess(req, resp); // ?????? ??????
			responseDto.setStatus(ResponseStatus.SUCCESS);
			break;
			case SIGNUP: responseDto = userService.signup(userRequestDto); // ?????? ?????? DB ??????
			break;
			case CHECK_DUPLICATED_EMAIL: responseDto = userService.checkDuplicatedEmail(userRequestDto); // ????????? ?????? ??????
				break;
			case CHECK_DUPLICATED_NICKNAME: responseDto = userService.checkDuplicatedNickname(userRequestDto); // ????????? ?????? ?????? 
				break;
			case CHECK_DUPLICATED_USER_ID: responseDto = userService.checkDuplicatedUserId(userRequestDto); // ????????? ?????? ??????
				break;
			case GET_ID_CHECK_COEDE: responseDto = userService.getIdCheckCode(userRequestDto); // ????????? ????????? ?????? ?????? ??????
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
		UserRequestDto userRequestDto = setRequestDto(req);
		ResponseDto<?> responseDto = new ResponseDto();
		switch (uri) {
		case REQUEST_EMAIL: responseDto = userService.requestEmail(userRequestDto);
		break;
		case LOGIN: responseDto = userService.login(userRequestDto); // ?????????
		loginProcess(responseDto,req, resp);
			break;
		case GET_ID_CHECK_COEDE: responseDto = userService.findUserId(userRequestDto); // ????????? ????????? ?????? ?????? ??????
			break;
		case CHECK_PASSWORD_CODE:
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
		Object object = new Object();
		switch (uri) {
		case GET_ID_CHECK_COEDE:
			break;
		
		default:
			break;
		}
	}
	
	// ?????? ?????? ?????????
	private void logoutProcess(HttpServletRequest req, HttpServletResponse resp) {
		// ?????? ????????? ??????
		String userSession = getUserSession(req);
		// ?????? ?????? ??????
		req.getSession().removeAttribute(userSession);
		// ?????? ?????? ??????????????? ?????? ?????? ??????
		sessionStore.remove(userSession);
		// ?????? ??????
		deleteCookie(req, resp);
	}


	// ????????? ?????????
	private void loginProcess(ResponseDto<?> responseDto, HttpServletRequest req, HttpServletResponse resp) {
		if(responseDto.getStatus().equals(ResponseStatus.SUCCESS)) {
			// ?????? ?????? ??????
			String token = UUID.randomUUID().toString();
			// ????????? ????????? ????????? ??????
			req.getSession().setAttribute("userSession", token);
			// ?????? ?????? ??????
			req.getSession().setMaxInactiveInterval(60*60*24*1); //???*???*???*???
			// ????????? ????????? ??? ?????? ?????? ?????? ???????????? ??????
			sessionStore.put(token, (UserResponseDto)responseDto.getData());
			// ?????? ??????
			deleteCookie(req, resp);
			
			int maxAge=60*60*24*1;
			resp.addHeader("Set-Cookie", "userSession="+token+"; path=/; MaxAge="+maxAge+"; Secure; SameSite=None; HttpOnly");
			
//	        Cookie sessionCookie = new Cookie("userSession", token);
//	        sessionCookie.setPath("/");
//	        sessionCookie.setMaxAge(60*60*24*1); //???*???*???*???
//	        sessionCookie.setHttpOnly(false);
//	        resp.addCookie(sessionCookie);
		}
	}

	// ???????????? ?????? ????????? ??????
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
	
	//?????? ??????
	private void deleteCookie(HttpServletRequest req, HttpServletResponse resp) {
		Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userSession")) {
                	cookie = new Cookie("userSession", "");
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        } 
	}

	// ?????? ????????? json ??????
	private void responseData(HttpServletResponse resp, ResponseDto responseDto) throws IOException {
		resp.setContentType("application/json; charset=utf-8");	
//		resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
//
//		resp.setHeader("Access-Control-Allow-Credentials", "true");
//		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//		resp.setHeader("Access-Control-Max-Age", "3600");
//		resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With");
		try(PrintWriter out = resp.getWriter();){
			// ?????? ?????? ??????
			out.print("{\"status\":");
			out.print("\""+ responseDto.getStatus().toString() +"\"");
			out.print(",");
			// ????????? ??????
			out.print("\"data\":");
			try {
				out.append(responseDto.getData().toString());
			}catch (Exception e) {
				out.print("[]");
			}
			out.print("}");
		}
	}
	
	// ?????? ????????? dto ??????
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
		System.out.println(userRequestDto.toString());
		return userRequestDto;
	}
	
	
}
