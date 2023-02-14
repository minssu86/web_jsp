<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>edit-pw</title>

  <!-- global css -->
  <link rel="stylesheet" href="../css/global.css">
  <!-- local css -->
  <link rel="stylesheet" href="../css/edit_pw.css">
  <style rel="stylesheet">
  </style>
</head>
<body>
<jsp:include page="../template/header.jsp"></jsp:include>
<main>
  <section>
    <h2>비밀번호 재설정</h2>
    <form name="edit-pw" action="#">
      <article>
        <ul>
          <li>
            <label for="edit-pw">
                새 비밀번호
            </label>
              <input id="edit-pw" type="password" placeholder="비밀번호를 입력해주세요">
            <div><h3>영문/숫자/특수문자 중 2가지 이상, 8자 이상</h3></div>
          </li>
          <li>
            <label for="edit-check">
                비밀번호 확인
            </label>
              <input id="edit-check" type="password" placeholder="비밀번호를 한번 더 입력해주세요">
            <div><h3>비밀번호가 일치하지 않습니다</h3></div>
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