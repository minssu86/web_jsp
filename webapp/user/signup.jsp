<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>signup</title>
    <!-- local css -->
    <link rel="stylesheet" href="../css/signup.css">
    <!-- global css & javascript -->
    <jsp:include page="../template/global.jsp"></jsp:include>
    <!-- singup javascript -->
    <script type="text/javascript" src="../js/singup.js"></script>
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