<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>board detail</title>
    <!-- local css -->
    <link rel="stylesheet" href="../css/board_detail.css">
    <!-- global css & javascript -->
    <jsp:include page="../template/global.jsp"></jsp:include>
    <!-- local javascript -->
    <script type="text/javascript" src="../js/board_detail.js"></script>
</head>
<body>
<jsp:include page="../template/header.jsp"></jsp:include>
<main>
    <section>
        <article>
            <div>
                <a href="write.jsp">
                    글쓰기
                    <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <g clip-path="url(#clip0_138_143)">
                            <path d="M2 11.5V14H4.5L11.8733 6.62667L9.37333 4.12667L2 11.5ZM3.94667 12.6667H3.33333V12.0533L9.37333 6.01333L9.98667 6.62667L3.94667 12.6667ZM13.8067 3.75333L12.2467 2.19333C12.1133 2.06 11.9467 2 11.7733 2C11.6 2 11.4333 2.06667 11.3067 2.19333L10.0867 3.41333L12.5867 5.91333L13.8067 4.69333C14.0667 4.43333 14.0667 4.01333 13.8067 3.75333V3.75333Z"
                                  fill="white"/>
                        </g>
                        <defs>
                            <clipPath id="clip0_138_143">
                                <rect width="16" height="16" fill="white"/>
                            </clipPath>
                        </defs>
                    </svg>
                </a>
            </div>
            <div>
                <a href="list.jsp">목록</a>
            </div>
        </article>
        <article>
            <div>
                <h2>제목총45글자로한줄제목총45글자로한줄제목총45글자로한줄제목총45글자로한줄제목총45</h2>
                <div>
                    <div><div><img alt="" src=""/></div>닉네임열두자리까지만허용</div>
                    <div>2022.9.14 16:24 수정됨</div>
                    <div>
                        <div>
                            <h3>12</h3>
                            <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <g clip-path="url(#clip0_24_2152)">
                                    <path d="M2.66683 2.66667H13.3335V10.6667H3.44683L2.66683 11.4467V2.66667ZM2.66683 1.33334C1.9335 1.33334 1.34016 1.93334 1.34016 2.66667L1.3335 14.6667L4.00016 12H13.3335C14.0668 12 14.6668 11.4 14.6668 10.6667V2.66667C14.6668 1.93334 14.0668 1.33334 13.3335 1.33334H2.66683ZM4.00016 8H9.3335V9.33334H4.00016V8ZM4.00016 6H12.0002V7.33334H4.00016V6ZM4.00016 4H12.0002V5.33334H4.00016V4Z" fill="#8E8E95"/>
                                </g>
                                <defs>
                                    <clipPath id="clip0_24_2152">
                                        <rect width="16" height="16" fill="white"/>
                                    </clipPath>
                                </defs>
                            </svg>
                        </div>
                        <div>
                            <h3>54</h3>
                            <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <g clip-path="url(#clip0_24_2149)">
                                    <path d="M5.99984 14H11.9998C12.5532 14 13.0265 13.6667 13.2265 13.1867L15.2398 8.48666C15.2998 8.33333 15.3332 8.17333 15.3332 8V6.66666C15.3332 5.93333 14.7332 5.33333 13.9998 5.33333H9.79317L10.4265 2.28666L10.4465 2.07333C10.4465 1.8 10.3332 1.54666 10.1532 1.36666L9.4465 0.666664L5.05317 5.06C4.81317 5.3 4.6665 5.63333 4.6665 6V12.6667C4.6665 13.4 5.2665 14 5.99984 14ZM5.99984 6L8.89317 3.10666L7.99984 6.66666H13.9998V8L11.9998 12.6667H5.99984V6ZM0.666504 6H3.33317V14H0.666504V6Z" fill="#8E8E95"/>
                                </g>
                                <defs>
                                    <clipPath id="clip0_24_2149">
                                        <rect width="16" height="16" fill="white"/>
                                    </clipPath>
                                </defs>
                            </svg>
                        </div>
                        <div>
                            <h3>14654</h3>
                            <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <g clip-path="url(#clip0_24_2146)">
                                    <path d="M7.99984 4.33333C10.5265 4.33333 12.7798 5.75333 13.8798 8C12.7798 10.2467 10.5332 11.6667 7.99984 11.6667C5.4665 11.6667 3.21984 10.2467 2.11984 8C3.21984 5.75333 5.47317 4.33333 7.99984 4.33333ZM7.99984 3C4.6665 3 1.81984 5.07333 0.666504 8C1.81984 10.9267 4.6665 13 7.99984 13C11.3332 13 14.1798 10.9267 15.3332 8C14.1798 5.07333 11.3332 3 7.99984 3ZM7.99984 6.33333C8.91984 6.33333 9.6665 7.08 9.6665 8C9.6665 8.92 8.91984 9.66667 7.99984 9.66667C7.07984 9.66667 6.33317 8.92 6.33317 8C6.33317 7.08 7.07984 6.33333 7.99984 6.33333ZM7.99984 5C6.3465 5 4.99984 6.34667 4.99984 8C4.99984 9.65333 6.3465 11 7.99984 11C9.65317 11 10.9998 9.65333 10.9998 8C10.9998 6.34667 9.65317 5 7.99984 5Z" fill="#8E8E95"/>
                                </g>
                                <defs>
                                    <clipPath id="clip0_24_2146">
                                        <rect width="16" height="16" fill="white"/>
                                    </clipPath>
                                </defs>
                            </svg>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <p>본문몇자까지를허용???? texteditor적용하나???</p>
            </div>
        </article>
        <article>
            <button>
                <svg width="18" height="19" viewBox="0 0 18 19" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <g clip-path="url(#clip0_59_426)">
                        <path d="M16.5 7.43L11.1075 6.965L9 2L6.8925 6.9725L1.5 7.43L5.595 10.9775L4.365 16.25L9 13.4525L13.635 16.25L12.4125 10.9775L16.5 7.43ZM9 12.05L6.18 13.7525L6.93 10.5425L4.44 8.3825L7.725 8.0975L9 5.075L10.2825 8.105L13.5675 8.39L11.0775 10.55L11.8275 13.76L9 12.05Z" fill="#1E1E20"/>
                    </g>
                    <defs>
                        <clipPath id="clip0_59_426">
                            <rect width="18" height="18" fill="white" transform="translate(0 0.5)"/>
                        </clipPath>
                    </defs>
                </svg>
                <span>추천</span>
            </button>
        </article>
        <article>
            <div>
                <div>
                    <div>
                        <div>
                            <div>
                                <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <g clip-path="url(#clip0_145_30)">
                                        <path d="M3 3H15V12H3.8775L3 12.8775V3ZM3 1.5C2.175 1.5 1.5075 2.175 1.5075 3L1.5 16.5L4.5 13.5H15C15.825 13.5 16.5 12.825 16.5 12V3C16.5 2.175 15.825 1.5 15 1.5H3ZM4.5 9H10.5V10.5H4.5V9ZM4.5 6.75H13.5V8.25H4.5V6.75ZM4.5 4.5H13.5V6H4.5V4.5Z" fill="#1E1E20"/>
                                    </g>
                                    <defs>
                                        <clipPath id="clip0_145_30">
                                            <rect width="18" height="18" fill="white"/>
                                        </clipPath>
                                    </defs>
                                </svg>
                                <span>댓글</span>
                                <span>99999</span>
                            </div>
                            <button>최신순</button>
                            <button>등록순</button>
                        </div>
                        <div>
                            <label>
                                <textarea placeholder="댓글을 남겨보세요"></textarea>
                            </label>
                            <div>
                                <span>0</span>
                                <span>/2000자</span>
                                <button>등록</button>
                            </div>
                        </div>
                    </div>
                    <div>
                        <ul>
                            <li>
                                <article class="comment-parent">
                                    <p>
                                        <span>nick12자까지만허용</span>
                                        <span>2022.9.14 16:24</span>
                                        <button>수정</button>
                                        <button>답글쓰기</button>
                                        <button>삭제</button>
                                        <span>
                                <svg width="16" height="17" viewBox="0 0 16 17" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <g clip-path="url(#clip0_159_210)">
                                <path d="M5.99984 14.5H11.9998C12.5532 14.5 13.0265 14.1667 13.2265 13.6867L15.2398 8.98669C15.2998 8.83335 15.3332 8.67335 15.3332 8.50002V7.16669C15.3332 6.43335 14.7332 5.83335 13.9998 5.83335H9.79317L10.4265 2.78669L10.4465 2.57335C10.4465 2.30002 10.3332 2.04669 10.1532 1.86669L9.4465 1.16669L5.05317 5.56002C4.81317 5.80002 4.6665 6.13335 4.6665 6.50002V13.1667C4.6665 13.9 5.2665 14.5 5.99984 14.5ZM5.99984 6.50002L8.89317 3.60669L7.99984 7.16669H13.9998V8.50002L11.9998 13.1667H5.99984V6.50002ZM0.666504 6.50002H3.33317V14.5H0.666504V6.50002Z" fill="#8E8E95"/>
                                </g>
                                <defs>
                                <clipPath id="clip0_159_210">
                                <rect width="16" height="16" fill="white" transform="translate(0 0.5)"/>
                                </clipPath>
                                </defs>
                                </svg>
                                99999
                                </span>
                                    </p>
                                    <label>
                                        <textarea readonly="readonly" rows="1" maxlength="2000">댓글 내용 띄어쓰기 포함 총 2000자 댓글 내용 띄어쓰기 포함 총 2000자</textarea>
                                    </label>
                                </article>
                            </li>
                            <li>
                                <div class="comment-write">
                                    <div>
                                        <label>
                                            <textarea placeholder="댓글을 남겨보세요"></textarea>
                                        </label>
                                        <div>
                                            <span>0</span>
                                            <span>/2000자</span>
                                            <button>등록</button>
                                        </div>
                                    </div>
                                </div>
                            </li>

                        </ul>
                    </div>
                </div>
                <div>
                    <div>
                        <a id="btn-edit-board">수정</a>
                        <a id="btn-delete-board">삭제</a>
                    </div>
                    <div>
                        <a href="list.jsp">목록</a>
                        <a>
                            맨 위로
                            <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <g clip-path="url(#clip0_138_189)">
                                    <path d="M2.6665 7.99996L3.6065 8.93996L7.33317 5.21996V13.3333H8.6665V5.21996L12.3865 8.94663L13.3332 7.99996L7.99984 2.66663L2.6665 7.99996Z" fill="#1E1E20"/>
                                </g>
                                <defs>
                                    <clipPath id="clip0_138_189">
                                        <rect width="16" height="16" fill="white"/>
                                    </clipPath>
                                </defs>
                            </svg>
                        </a>
                        <a href="write.jsp">
                            글쓰기
                            <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <g clip-path="url(#clip0_138_137)">
                                    <path d="M2 11.5V14H4.5L11.8733 6.62667L9.37333 4.12667L2 11.5ZM3.94667 12.6667H3.33333V12.0533L9.37333 6.01333L9.98667 6.62667L3.94667 12.6667ZM13.8067 3.75333L12.2467 2.19333C12.1133 2.06 11.9467 2 11.7733 2C11.6 2 11.4333 2.06667 11.3067 2.19333L10.0867 3.41333L12.5867 5.91333L13.8067 4.69333C14.0667 4.43333 14.0667 4.01333 13.8067 3.75333V3.75333Z" fill="white"/>
                                </g>
                                <defs>
                                    <clipPath id="clip0_138_137">
                                        <rect width="16" height="16" fill="white"/>
                                    </clipPath>
                                </defs>
                            </svg>
                        </a>
                    </div>
                </div>
            </div>
        </article>
    </section>
</main>
<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>