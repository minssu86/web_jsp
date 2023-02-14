<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>

    <!-- global css -->
    <link rel="stylesheet" href="../css/global.css">
    <!-- local css -->
    <link rel="stylesheet" href="../css/login.css">
</head>
<body>
<jsp:include page="../template/header.jsp"></jsp:include>
<main>
    <section>
        <h2>로그인</h2>
        <form name="login" action="#" method="post">
            <article id="basic-login">
                <label>
                    <input type="text" name="id" placeholder="아이디를 입력해주세요"/>
                </label>
                <label>
                    <input type="password" name="password" placeholder="비밀번호를 입력해주세요"/>
                </label>
                <button>로그인</button>
                <ul>
                    <li><a href="search_id.jsp">아이디 찾기</a></li>
                    <li><div></div></li>
                    <li><a href="search_pw.jsp">비밀번호 찾기</a></li>
                    <li><div></div></li>
                    <li><a href="signup.jsp">회원가입</a></li>
                </ul>
            </article>
            <article id="social-login">
                <button type="button">
                    <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M7.99956 0.442993C3.81333 0.442993 0 3.30288 0 6.83065C0 9.02402 1.38489 10.9591 3.49422 12.1088L2.60667 15.4597C2.52844 15.7568 2.856 15.9923 3.108 15.8208L6.99822 13.1667C7.32667 13.1996 7.66044 13.2183 7.99956 13.2183C12.4182 13.2183 15.9996 10.3584 15.9996 6.83065C15.9996 3.30288 12.4182 0.442993 7.99956 0.442993Z" fill="#1E1E20"/>
                    </svg>
                    <span>카카오로 시작하기</span>
                </button>
            </article>
        </form>
    </section>
</main>
<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>