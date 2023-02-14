<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>search-id-result</title>

  <!-- global css -->
  <link rel="stylesheet" href="../css/global.css">
  <!-- local css -->
  <link rel="stylesheet" href="../css/search_id_result.css">
</head>
<body>
<jsp:include page="../template/header.jsp"></jsp:include>
<main>
  <section>
    <h2>아이디 찾기</h2>
    <article >
      <h3>고객님의 계정을 찾았습니다</h3>
      <p>아이디를 확인 후 로그인 해주세요</p>
      <div>
        <h3>qwer1234</h3>
        <p>가입일 2022.03.12</p>
      </div>
    </article>
    <article >
       <div>
        <a href="search_pw.jsp">
          비밀번호 찾기
        </a>
      </div>
      <div>
        <a href="login.jsp">
          로그인
        </a>
      </div>
    </article>
  </section>
</main>
<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>