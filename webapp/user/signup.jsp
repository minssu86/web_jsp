<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>signup</title>
    <!-- global css -->
    <link rel="stylesheet" href="../css/global.css">
    <!-- local css -->
    <link rel="stylesheet" href="../css/signup.css">
    <style rel="stylesheet">
    </style>
    <script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        const sucess = 'SUCCESS';
        let isCheckedId = false; // 아이디 중복
        let isCheckedNickname = false; // 닉네임 중복
        let isCheckedEmail = false; // 이메일 중복

        let passwordSame = false;
        
        let checkPassword = false; // 패스워드 규정
        let checkNickname = false; // 이름 규정

    	$(function(){
            //비밀번호 입력 체크
            $('#signup-pw').keyup(function(){
                let input = $(this).val();
                let lengthRegex = /^[A-Za-z0-9!@#$%]{8,20}$/;
                let engRegex = /[A-Za-z]/;
                let digitRegex = /[0-9]/;
                let specRegex = /[!@#$%]/;
                let result = '영문/숫자/특수문자 중 2가지 이상, 8자 이상 20자 이하';
                let safetyCount = 0;
                if (lengthRegex.exec(input)) {
                    safetyCount = 0;
                    if (engRegex.exec(input)) safetyCount++;
                    if (digitRegex.exec(input)) safetyCount++;
                    if (specRegex.exec(input)) safetyCount++;
                    switch (safetyCount) {
                        case 3:
                            result = '안전';
                            checkPassword = true; // 전역변수값을 true 로 변경
                            $('#result-pw-check').css({'color':'green'});
                            break;
                        case 2:
                            result = '보통';
                            checkPassword = true; // 전역변수값을 true 로 변경
                            $('#result-pw-check').css({'color':'blue'});
                            break;
                        case 1:
                            result  = '사용불가';
                            checkPassword = false; // 전역변수값을 false 로 변경
                            $('#result-pw-check').css({'color':'red'});
                            break;
                    }
                } else {
                    result = '영문/숫자/특수문자 중 2가지 이상, 8자 이상 20자 이하';
                    $('#result-pw-check').css({'color':'#8E8E95'});
                }
                $('#result-pw-check').text(result);
                passwordSameCheck($('#signup-check').val());
            });
            //비밀번호 확인 체크 
            $('#signup-check').keyup(function(){
                let input = $(this).val();
                passwordSameCheck(input);
            });
            let passwordSameCheck = function(input){
                let password = $('#signup-pw').val();
                if (password == input && input!='') { // 패스워드 확인 내용 일치 시
                    $('#result-pw-same-check').text('비밀번호가 일치합니다');
                    $('#result-pw-same-check').css({'color':'green'});
                    passwordSame = true; // 전역변수값을 true 로 변경
                } else {
                    $('#result-pw-same-check').text('비밀번호가 일치하지 않습니다');
                    $('#result-pw-same-check').css({'color':'#ED7474'});
                    passwordSame = false; // 전역변수값을 false 로 변경
                }
            };

            //닉네임 입력 체크
            $('#signup-nickname').keyup(function(){
                isCheckedNickname = false;
                let input = $(this).val();
                let regex = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{2,10}$/; // 2~10자리 영문,숫자 조합 정규 표현식 활용
                if (regex.exec(input)) {
                    if(isCheckedNickname){
                        $('#result-nick-check').text('사용 가능');
                        $('#result-nick-check').css({'color':'green'});
                    } else {
                        $('#result-nick-check').text('중복 확인이 필요합니다');
                        $('#result-nick-check').css({'color':'blue'});
                    }
                    checkNickname = true; // 전역변수값을 true 로 변경
                } else {
                    $('#result-nick-check').text('2~10자 한글, 영문 대소문자만 사용하세요');
                    $('#result-nick-check').css({'color':'#ED7474'});
                    checkNickname = false; // 전역변수값을 false 로 변경
                }
            });

            // 아이디 중복 체크
            $('#duplicate-id').click(function(){
                let id = $('#signup-id').val();
                if(id==''){
                    alert("아이디를 입력하세요 : 모달로 바꿔야함!")
                }
                $.getJSON('../v1/user/duplication/id?id='+id,function(data){
                    let result = data.status;
                    if(result==sucess){
                        isCheckedId = true;
                        alert("사용 가능 : 모달로 바꿔야함!")
                    } else {
                        isCheckedId = false;
                        alert("아이디 중복 : 모달로 바꿔야함!")
                    }
                });
            });

            // 닉네임 중복 체크
            $('#duplicate-nickname').click(function(){
                let nickname = $('#signup-nickname').val();
                if(nickname==''){
                    alert("닉네임을 입력하세요 : 모달로 바꿔야함!")
                }
                $.getJSON('../v1/user/duplication/nickname?nickname='+nickname,function(data){
                    let result = data.status;
                    if(result==sucess){
                        isCheckedNickname = true;
                        $('#result-nick-check').text('사용 가능');
                        $('#result-nick-check').css({'color':'green'});
                        alert("사용 가능 : 모달로 바꿔야함!")
                    } else {
                        isCheckedNickname = false;
                        alert("닉네임 중복 : 모달로 바꿔야함!")
                    }
                });
            });

            // 이메일 중복 체크
            $('#duplicate-email').click(function(){
                let email = $('#signup-email').val();
                if(email==''){
                    alert("이메일을 입력하세요 : 모달로 바꿔야함!")
                }
                $.getJSON('../v1/user/duplication/email?email='+email,function(data){
                    let result = data.status;
                    if(result==sucess){
                        isCheckedEmail = true;
                        alert("사용 가능 : 모달로 바꿔야함!")
                    } else {
                        isCheckedEmail = false;
                        alert("이메일 중복 : 모달로 바꿔야함!")
                    }
                });
            });

            // 제출 전 마지막 체크
            $('#submit').click(function(){
                console.log("??");
                let isPasswordSame = $('#signup-pw').val() == $('#signup-check').val;
                switch (false){
                    case isCheckedId :
                        alert("아이디 중복 체크 필요")
                        return;
                    case isCheckedNickname :
                        alert("닉네임 중복 체크 필요")
                        return;
                    case isCheckedEmail :
                        alert("이메일 중복 체크 필요")
                        return;
                    case passwordSame :
                        alert("비밀번호 확인 필요")
                        return;
                }
                console.log("ok");
            }); 
            
        });

     

    </script>
</head>
<body>
<jsp:include page="../template/header.jsp"></jsp:include>
<main>
    <section>
        <h2>회원가입</h2>
        <form name="form-signup" action="../v1/user/signup" method="post">
            <article>
                <ul>
                    <li>
                        <label for="signup-id">
                            아이디
                        </label>
                        <input id="signup-id" name="id" type="text" placeholder="아이디를 입려해주세요 (최대 20자)" maxlength="20">
                        <button id="duplicate-id" type="button">중복 확인</button>
                    </li>
                    <li>
                        <label for="signup-pw">
                            비밀번호
                        </label>
                        <input id="signup-pw" name="password" type="password" placeholder="비밀번호를 입력해주세요" minlength="8" maxlength="20">
                        <div><h3 id="result-pw-check">영문/숫자/특수문자 중 2가지 이상, 8자 이상 20자 이하</h3></div>
                    </li>
                    <li>
                        <label for="signup-check">
                            비밀번호 확인
                        </label>
                        <input id="signup-check" name="passwordchek" type="password" placeholder="비밀번호를 한번 더 입력해주세요" minlength="8">
                        <div><h3 id="result-pw-same-check">비밀번호가 일치하지 않습니다</h3></div>
                    </li>
                    <li>
                        <label for="signup-name">
                            이름
                        </label>
                        <input id="signup-name" name="name" type="text" placeholder="이름을 입력해주세요">
                    </li>
                    <li>
                        <label for="signup-nickname">
                            닉네임
                        </label>
                        <input id="signup-nickname" name="nickname" type="text" placeholder="닉네임을 입력해주세요 (최대 10자)" maxlength="10">
                        <button id="duplicate-nickname" type="button">중복 확인</button>
                        <div><h3 id="result-nick-check">2~10자 한글, 영문 대소문자를 사용할 수 있습니다</h3></div>
                    </li>
                    <li>
                        <label for="signup-email">
                            이메일
                        </label>
                        <input id="signup-email" name="email" type="email" placeholder="이메일을 입력해주세요">
                        <button id="duplicate-email" type="button">중복 확인</button>
                    </li>
                </ul>
            </article>
            <div>
                <button type="button" id="submit">가입하기</button>
            </div>
        </form>
    </section>
</main>
<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>