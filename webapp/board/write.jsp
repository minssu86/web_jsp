<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>write</title>
    <!-- local css -->
    <link rel="stylesheet" href="../css/board_write.css">
    <!-- global css & javascript -->
    <jsp:include page="../template/global.jsp"></jsp:include>
    <!-- local javascript -->
    <script type="text/javascript" src="../js/board_write.js"></script>
</head>
<body>
<jsp:include page="../template/header.jsp"></jsp:include>
<main>
    <section>
        <form name="board-write">
            <article>
                <h2>게시글 작성</h2>
                <div>
                    <label for="write-title">제목</label>
                    <div>
                        <input id="write-title" type="text" placeholder="제목을 입력해주세요.">
                    </div>
                </div>
            </article>
            <article>
                <label>
                    <textarea id="textarea-write-content" placeholder="내용을 입력해주세요."></textarea>
                </label>
            </article>
            <article>
                <div>
                    <button class="btn" id="btn-back" type="button">취소</button>
                    <button class="btn" id="btn-write-board" type="button">작성 완료</button>
                </div>
            </article>
        </form>
    </section>
</main>
<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>