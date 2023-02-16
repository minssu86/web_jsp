<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>search-pw</title>
  <!-- local css -->
  <link rel="stylesheet" href="../css/search.css">
  <!-- global css & javascript -->
  <jsp:include page="../template/global.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../template/header.jsp"></jsp:include>
<main>
  <section>
    <h2>비밀번호 찾기</h2>
    <form name="search-pw" action="#">
      <article>
        <ul>
          <li>
            <label for="search-pw-id">
                아이디
            </label>
              <input id="search-pw-id" type="text" placeholder="이름을 입력해주세요">
          </li>
          <li>
            <label for="search-pw-email">
                E-mail
            </label>
              <input id="search-pw-email" type="email" placeholder="이메일을 입력해주세요">
            <button type="button">인증번호 받기</button>
          </li>
          <li>
            <label for="search-pw-code">
                인증번호
            </label>
              <input id="search-pw-code" type="text" placeholder="인증번호를 입력해주세요">
          </li>
        </ul>
      </article>
      <div><button>확인</button></div>
    </form>
  </section>
</main>
<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>