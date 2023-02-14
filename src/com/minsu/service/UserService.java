package com.minsu.service;

import com.minsu.dao.UserDao;
import com.minsu.dto.ResponseDto;
import com.minsu.dto.ResponseStatus;
import com.minsu.dto.UserRequestDto;
import com.minsu.dto.UserResponseDto;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    public static Map<String, UserResponseDto> idCheckCodeMap = new HashMap<>();
    public static Map<String, UserRequestDto> idCreateCodeMap = new HashMap<>();

    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    // 회원 가입
    public ResponseDto<?> signup(UserRequestDto userRequestDto) {
        // 데이터 암호화 필요, 특히 비밀번호
        boolean isSuccess = userDao.save(userRequestDto);
        if(isSuccess)return new ResponseDto(ResponseStatus.SUCCESS);
        return new ResponseDto(ResponseStatus.FAIL);
    }

    // 회원 가입 이메일 요청
    public ResponseDto<?> requestEmail(UserRequestDto userRequestDto) {
        // 데이터 암호화 필요, 특히 비밀번호
    	if(checkDuplicatedEmail(userRequestDto).getStatus().equals(ResponseStatus.FAIL) 
    			|| !checkDuplicatedNickname(userRequestDto).getStatus().equals(ResponseStatus.FAIL)
    			|| !checkDuplicatedUserId(userRequestDto).getStatus().equals(ResponseStatus.FAIL)) {
    		return new ResponseDto(ResponseStatus.FAIL);
    	}
    	idCreateCodeMap.put("testCheck", userRequestDto);
        return new ResponseDto(ResponseStatus.SUCCESS);
    }

    // 이메일 중복 확인
    public ResponseDto<?> checkDuplicatedEmail(UserRequestDto userRequestDto) {
    	if(userRequestDto.getUserEmail()==null) return new ResponseDto(ResponseStatus.FAIL);
        if(userDao.findByEmail(userRequestDto.getUserEmail()))
        	return new ResponseDto(ResponseStatus.SUCCESS);
        return new ResponseDto(ResponseStatus.FAIL);
    }

    // 닉네임 중복 확인
    public ResponseDto<?> checkDuplicatedNickname(UserRequestDto userRequestDto) {
    	if(userRequestDto.getUserNickname()==null) return new ResponseDto(ResponseStatus.FAIL);
        if(userDao.findByUserNickname(userRequestDto.getUserNickname()))
        	return new ResponseDto(ResponseStatus.SUCCESS);
        return new ResponseDto(ResponseStatus.FAIL);
    }

    // 아이디 중복 확인
    public ResponseDto<?> checkDuplicatedUserId(UserRequestDto userRequestDto) {
    	if(userRequestDto.getUserId()==null) return new ResponseDto(ResponseStatus.FAIL);
        if(userDao.findByUserId(userRequestDto.getUserId()))
        	return new ResponseDto(ResponseStatus.SUCCESS);
        return new ResponseDto(ResponseStatus.FAIL);
    }

    // 로그인
    public String login(UserRequestDto userRequestDto) {
        boolean isSuccess = userDao.findByUserIdAndPassword(userRequestDto.getUserId(), userRequestDto.getUserPassword());
        if(isSuccess)return "성공";
        return "실패";
    }

    // 아이디 찾기
    public UserResponseDto findUserId(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = idCheckCodeMap.get(userRequestDto.getCheckCode());
        if(userResponseDto!=null
                && userResponseDto.getUserName().equals(userRequestDto.getUserName())
                && userResponseDto.getUserEmail().equals(userRequestDto.getUserEmail())){
            idCheckCodeMap.remove(userRequestDto.getCheckCode());
            return userResponseDto;
        }
        return null;
    }

    // 아이디 찾기 : 인증 코드 요청
    // parameter : name, email
    public String getIdCheckCode(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userDao.findByUserNameAndUserEmail(userRequestDto.getUserName(), userRequestDto.getUserEmail());
        if(userResponseDto!=null){
            String checkCode = "1234";
            idCheckCodeMap.put(checkCode,userResponseDto);
            return "성공";
        }
        return "실패";
    }

    // 비밀번호 찾기 : 인증 코드 요청
    public String getPasswordCheckCode(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userDao.findByUserIdAndUserEmail(userRequestDto.getUserId(), userRequestDto.getUserEmail());
        if(userResponseDto!=null){
            String checkCode = "4321";
            idCheckCodeMap.put(checkCode,userResponseDto);
            return "성공";
        }
        return "실패";
    }

    // 비밀번호 찾기 : 인증 번호 확인
    public boolean checkPasswordCode(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = idCheckCodeMap.get(userRequestDto.getCheckCode());
        return userResponseDto != null
                && userResponseDto.getUserId().equals(userRequestDto.getUserId())
                && userResponseDto.getUserEmail().equals(userRequestDto.getUserEmail());
    }

    // 비밀번호 재설정
    public String changePassword(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = idCheckCodeMap.get(userRequestDto.getCheckCode());
        boolean isSuccess = userDao.updatePassword(userResponseDto.getUserId(), userRequestDto.getUserPassword());
        if(isSuccess){
            idCheckCodeMap.remove(userRequestDto.getCheckCode());
            return "성공";
        }
        return "실패";
    }

}
