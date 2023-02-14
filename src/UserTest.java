import com.minsu.dto.UserRequestDto;
import com.minsu.dto.UserResponseDto;
import com.minsu.service.UserService;

public class UserTest {

    String result = "";
    UserService userService = new UserService();
    UserRequestDto userRequestDto = new UserRequestDto();

    public void signup(){

        userRequestDto.setUserEmail("email@test.email");
        userRequestDto.setUserName("nameTest");
        userRequestDto.setUserNickname("nickTest");
        userRequestDto.setUserPassword("passTest");
        userRequestDto.setUserId("idTest");
        System.out.println(userService.signup(userRequestDto));

    }

    public void checkDuplicatedEmail(String email){
        userRequestDto.setUserEmail(email);
        System.out.println(userService.checkDuplicatedEmail(userRequestDto));
    }

    public void checkDuplicatedNickname(String nickTest) {
        userRequestDto.setUserNickname(nickTest);
        System.out.println(userService.checkDuplicatedNickname(userRequestDto));
    }

    public void checkDuplicatedUserId(String idTest) {
        userRequestDto.setUserId(idTest);
        System.out.println(userService.checkDuplicatedUserId(userRequestDto));
    }

    public void login(String idTest, String passTest) {
        userRequestDto.setUserId(idTest);
        userRequestDto.setUserPassword(passTest);
        System.out.println(userService.login(userRequestDto));
    }


    public void getIdCheckCode(String nameTest, String email) {
        userRequestDto.setUserName(nameTest);
        userRequestDto.setUserEmail(email);
        System.out.println(userService.getIdCheckCode(userRequestDto));
        UserResponseDto userResponseDto = UserService.idCheckCodeMap.get("1234");
        System.out.println(userResponseDto.getUserId());
        System.out.println(userResponseDto.getUserName());
        System.out.println(userResponseDto.getUserEmail());
        System.out.println("=========================");
    }

    public void findUserId(String checkCode, String nameTest, String email) {
        userRequestDto.setCheckCode(checkCode);
        userRequestDto.setUserName(nameTest);
        userRequestDto.setUserEmail(email);
        UserResponseDto userResponseDto = userService.findUserId(userRequestDto);
        if(userResponseDto==null){
            System.out.println("실패");
        } else {
            System.out.println(userResponseDto.getUserId());
            System.out.println(userResponseDto.getCreatedAt());
            System.out.println(UserService.idCheckCodeMap.get("1234")==null);
        }
    }

    public void getPasswordCheckCode(String userId, String email) {
        userRequestDto.setUserId(userId);
        userRequestDto.setUserEmail(email);
        System.out.println(userService.getPasswordCheckCode(userRequestDto));
        UserResponseDto userResponseDto = UserService.idCheckCodeMap.get("4321");
        System.out.println(userResponseDto.getUserId());
        System.out.println(userResponseDto.getUserEmail());
        System.out.println("=========================");
    }

    public void checkPasswordCode(String checkCode, String userId, String email) {
        userRequestDto.setCheckCode(checkCode);
        userRequestDto.setUserId(userId);
        userRequestDto.setUserEmail(email);
        boolean isSuccess = userService.checkPasswordCode(userRequestDto);
        System.out.println(isSuccess);
    }

    public void changePassword(String checkCode, String idTest, String changePass) {
        userRequestDto.setCheckCode(checkCode);
        userRequestDto.setUserId(idTest);
        userRequestDto.setUserPassword(changePass);
        System.out.println(userService.changePassword(userRequestDto));
        System.out.println(UserService.idCheckCodeMap.get("4321")==null);
    }
}
