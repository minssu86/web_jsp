<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>edit</title>
  <!-- local css -->
  <link rel="stylesheet" href="../css/board_write.css">
  <!-- global css & javascript -->
  <jsp:include page="../template/global.jsp"></jsp:include>
  <!-- local javascript -->
  <script type="text/javascript" src="../js/board_edit.js"></script>
</head>
<body>
<jsp:include page="../template/header.jsp"></jsp:include>
<main>
  <section>
    <form name="board-write">
      <article>
        <h2>게시글 수정</h2>
        <div>
          <label for="write-title">제목</label>
          <div>
            <input id="write-title" type="text" placeholder="제목을 입력해주세요.">
          </div>
        </div>
      </article>
      <article>
        <label>
          <textarea id="textarea-edit-content" placeholder="내용을 입력해주세요."></textarea>
        </label>
      </article>
      <article>
        <div>
          <button type="reset">취소</button>
          <button id="btn-edit-board" type="button">수정 완료</button>
        </div>
      </article>
    </form>
  </section>
</main>
<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>