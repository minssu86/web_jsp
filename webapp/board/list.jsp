<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>board list</title>
  <!-- local css -->
  <link rel="stylesheet" href="../css/board_list.css">
  <!-- global css & javascript -->
  <jsp:include page="../template/global.jsp"></jsp:include>
  <!-- local javascript -->
  <script type="text/javascript" src="../js/board_list.js"></script>
</head>
<body>
<jsp:include page="../template/header.jsp"></jsp:include>
<main>
  <section>
    <table>
      <caption>게시글 목록</caption>
      <thead>
        <tr>
          <th>글번호</th>
          <th>제목</th>
          <th>닉네임</th>
          <th>작성일</th>
          <th>조회수</th>
          <th>추천</th>
        </tr>
      </thead>
      <tbody class="tbody-list"></tbody>
      <tfoot>
        <tr>
          <td colspan="6">
            <div>
              <ul>
                <li>
                  <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <g clip-path="url(#clip0_124_93)">
                      <path d="M10.6948 10.065L9.80982 10.95L4.85982 6.00005L9.80982 1.05005L10.6948 1.93505L6.62982 6.00005L10.6948 10.065Z" fill="#8E8E95"/>
                      <path d="M7.14014 10.065L6.25514 10.95L1.30514 6.00005L6.25514 1.05005L7.14014 1.93505L3.07514 6.00005L7.14014 10.065Z" fill="#8E8E95"/>
                    </g>
                    <defs>
                      <clipPath id="clip0_124_93">
                        <rect width="12" height="12" fill="white"/>
                      </clipPath>
                    </defs>
                  </svg>
                </li>
                <li>
                  <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <g clip-path="url(#clip0_124_98)">
                      <path d="M9.08008 1.93501L8.19508 1.05001L3.24508 6.00001L8.19508 10.95L9.08008 10.065L5.01508 6.00001L9.08008 1.93501V1.93501Z" fill="#8E8E95"/>
                    </g>
                    <defs>
                      <clipPath id="clip0_124_98">
                        <rect width="12" height="12" fill="white"/>
                      </clipPath>
                    </defs>
                  </svg>
                </li>
                <li>1</li>
                <li>2</li>
                <li>3</li>
                <li>4</li>
                <li>5</li>
                <li>6</li>
                <li>7</li>
                <li>8</li>
                <li>9</li>
                <li>10</li>
                <li>
                  <svg width="7" height="10" viewBox="0 0 7 10" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M0.245117 9.06499L1.13012 9.94999L6.08012 4.99999L1.13012 0.0499878L0.245117 0.934988L4.31012 4.99999L0.245117 9.06499Z" fill="#8E8E95"/>
                  </svg>
                </li>
                <li>
                  <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <g clip-path="url(#clip0_124_126)">
                      <path d="M1.30518 1.93471L2.19018 1.04971L7.14018 5.99971L2.19018 10.9497L1.30518 10.0647L5.37018 5.99971L1.30518 1.93471Z" fill="#8E8E95"/>
                      <path d="M4.85986 1.93471L5.74486 1.04971L10.6949 5.99971L5.74486 10.9497L4.85986 10.0647L8.92486 5.99971L4.85986 1.93471Z" fill="#8E8E95"/>
                    </g>
                    <defs>
                      <clipPath id="clip0_124_126">
                        <rect width="12" height="12" fill="white"/>
                      </clipPath>
                    </defs>
                  </svg>
                </li>
              </ul>
            </div>
            <div>
              <a class="btn" href="../board/write.jsp">
                글쓰기
                <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <g clip-path="url(#clip0_124_147)">
                    <path d="M2 11.5V14H4.5L11.8733 6.62667L9.37333 4.12667L2 11.5ZM3.94667 12.6667H3.33333V12.0533L9.37333 6.01333L9.98667 6.62667L3.94667 12.6667ZM13.8067 3.75333L12.2467 2.19333C12.1133 2.06 11.9467 2 11.7733 2C11.6 2 11.4333 2.06667 11.3067 2.19333L10.0867 3.41333L12.5867 5.91333L13.8067 4.69333C14.0667 4.43333 14.0667 4.01333 13.8067 3.75333V3.75333Z" fill="white"/>
                  </g>
                  <defs>
                    <clipPath id="clip0_124_147">
                      <rect width="16" height="16" fill="white"/>
                    </clipPath>
                  </defs>
                </svg>
              </a>
            </div>
          </td>
        </tr>
      </tfoot>
    </table>
  </section>
</main>
<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>