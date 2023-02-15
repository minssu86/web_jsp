package com.minsu.service;

import com.minsu.dao.UserDao;
import com.minsu.dto.ResponseDto;
import com.minsu.dto.ResponseStatus;
import com.minsu.dto.UserRequestDto;
import com.minsu.dto.UserResponseDto;
import com.minsu.util.EmailConfirmTokenSender;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class UserService {
    public static Map<String, UserResponseDto> idCheckCodeMap = new HashMap<>();
    public static Map<String, UserRequestDto> idCreateCodeMap = new HashMap<>();

    private final UserDao userDao;
    private final EmailConfirmTokenSender emailConfirmTokenSender;

    public UserService() {
        this.userDao = new UserDao();
        this.emailConfirmTokenSender = new EmailConfirmTokenSender();
    }

    // 회원 가입
    public ResponseDto<?> signup(UserRequestDto userRequestDto) {
        // 데이터 암호화 필요, 특히 비밀번호
    	userRequestDto = idCreateCodeMap.get(userRequestDto.getCheckCode());
        boolean isSuccess = userDao.save(userRequestDto);
        if(isSuccess)return new ResponseDto(ResponseStatus.SUCCESS);
        return new ResponseDto(ResponseStatus.FAIL);
    }

    // 회원 가입 이메일 요청
    public ResponseDto<?> requestEmail(UserRequestDto userRequestDto) {
    	// 회원 정보 중복 확인
    	if(checkDuplicatedEmail(userRequestDto).getStatus().equals(ResponseStatus.FAIL) 
    			|| checkDuplicatedNickname(userRequestDto).getStatus().equals(ResponseStatus.FAIL)
    			|| checkDuplicatedUserId(userRequestDto).getStatus().equals(ResponseStatus.FAIL)) {
    		return new ResponseDto(ResponseStatus.FAIL);
    	}
    	// 비밀 번호 암호화
    	String encryptedPassword;
		try {
			encryptedPassword = encryptePassword(userRequestDto.getUserPassword());
	    	userRequestDto.setUserPassword(encryptedPassword);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			new ResponseDto(ResponseStatus.FAIL);
		}
    	// 인증 코드 생성
    	String code = userRequestDto.getUserEmail()+UUID.randomUUID();
    	userRequestDto.setCheckCode(code);
		// 회원 정보 임시 저장
    	idCreateCodeMap.put(code, userRequestDto);
    	// 인증 메일 발송
    	emailsend(code, userRequestDto.getUserEmail());
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
    public ResponseDto<?> login(UserRequestDto userRequestDto) {
        try {
        	// 유저 정보 확인
			UserResponseDto userResponseDto = userDao.findByUserIdAndPassword(
					userRequestDto.getUserId(),
					// 비밀번호 암호화
					encryptePassword(userRequestDto.getUserPassword()));
	        if(userResponseDto!=null)return new ResponseDto(ResponseStatus.SUCCESS);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
	        return new ResponseDto(ResponseStatus.FAIL);
		}
        return new ResponseDto(ResponseStatus.FAIL);
    }

    // 아이디 찾기 : 인증 코드 요청
    // parameter : name, email
    public ResponseDto<?> getIdCheckCode(UserRequestDto userRequestDto) {
        // 유저 정보 확인
    	System.out.println(userRequestDto.toString());
    	UserResponseDto userResponseDto = userDao.findByUserNameAndUserEmail(userRequestDto.getUserName(), userRequestDto.getUserEmail());
        System.out.println(userResponseDto.toString());
    	if(userResponseDto!=null){
        	// 인증 코드 생성
            String checkCode = createCheckCode();
            // 인증 코드 확인용 데이터 저장
            idCheckCodeMap.put(checkCode,userResponseDto);
            // 인증 코드 메일 전송
            checkCodeSend(checkCode, userResponseDto.getUserEmail());
            return new ResponseDto(ResponseStatus.SUCCESS);
        }
        return new ResponseDto(ResponseStatus.FAIL);
    }
    
    // 아이디 찾기
    public ResponseDto<?> findUserId(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = idCheckCodeMap.get(userRequestDto.getCheckCode());
        if(userResponseDto!=null
                && userResponseDto.getUserName().equals(userRequestDto.getUserName())
                && userResponseDto.getUserEmail().equals(userRequestDto.getUserEmail())){
            idCheckCodeMap.remove(userRequestDto.getCheckCode());
            return new ResponseDto<UserResponseDto>(ResponseStatus.SUCCESS, userResponseDto);
        }
        return new ResponseDto(ResponseStatus.FAIL);
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
    
    
    // SHA-256 단방향 암호화 (DB varchar(64) 필요)
    private String encryptePassword(String userPassword) throws NoSuchAlgorithmException {
    	/**
    	 * MessageDigest : 해시 함수를위한 클래스
    	 * getInstance(String algorithm) :
    	 * 		입력한 해시 알고리즘을 수행하는  MessageDigest 객체 생성 
    	 * 		(NoSuchAlgorithmException 발생 가능)
    	 * update(byte[] input) : 생성된 객체 내에 저장된 digest 값 갱신
    	 * digest : update()를 실행, 해시 계산 완료 후 해시화된 값(byte[])을 반환한다.
    	 */
    	MessageDigest md;
		md = MessageDigest.getInstance("SHA-256");
    	md.update(userPassword.getBytes());
    	byte[] data = md.digest();
    	
    	/**
    	 * byte[]배열을 DB저장을 위한 String 타입으로 변환
    	 */
    	StringBuilder hexPassword=new StringBuilder();
    	for(byte b : data) {
    		String hexString = String.format("%02x", b);
    		hexPassword.append(hexString);
    	}
		return hexPassword.toString();
	}
    
    // 인증 메일 발송 메서드
	private void emailsend(String code, String userEmail) {
		try {
        	Thread emailSend = new Thread(new Runnable() {
    			@Override
    			public void run() {
    				emailConfirmTokenSender.confirmTokenSend(code, userEmail);
    			}
    		});
    		emailSend.start();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}

	private void checkCodeSend(String code, String userEmail) {
		try {
        	Thread emailSend = new Thread(new Runnable() {
    			@Override
    			public void run() {
    				emailConfirmTokenSender.sendCheckCode(code, userEmail);
    			}
    		});
    		emailSend.start();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
    // 인증 코드 생성 메서드
    private String createCheckCode() {
		Random random = new Random();
		StringBuilder checkCode=new StringBuilder();
    	for(int i=0; i<8; i++) {
    		checkCode.append(random.nextInt(10));
    	}
		return checkCode.toString();
	}
}
