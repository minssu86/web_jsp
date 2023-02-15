<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>search-id</title>

  <!-- global css -->
  <link rel="stylesheet" href="../css/global.css">
  <!-- local css -->
  <link rel="stylesheet" href="../css/search.css">
  <!-- global javascript -->
  <script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script>
  <!-- login javascript -->
  <script type="text/javascript" src="../js/search_id.js"></script>
</head>
<body>
<jsp:include page="../template/header.jsp"></jsp:include>
<main>
  <section>
    <h2>아이디 찾기</h2>
    <form name="search-id" action="">
      <article>
        <ul>
          <li>
            <label for="search-id-name">
                이름
            </label>
              <input id="search-id-name" type="text" placeholder="이름을 입력해주세요">
          </li>
          <li>
            <label for="search-id-email">
                이메일
            </label>
              <input id="search-id-email" type="email" placeholder="이메일을 입력해주세요">
            <button id="btn-request-code" type="button">인증번호 받기</button>
          </li>
          <li>
          <label for="search-id-code">
              인증번호
          </label>
            <input id="search-id-code" type="text" placeholder="인증번호를 입력하세요">
        </li>
        </ul>
      </article>
      <div><button id="btn-send-formdata" type="button">확인</button></div>
    </form>
  </section>
</main>
<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>