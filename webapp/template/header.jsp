<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/header.css">
<script>
        $(function(){

            $('#input-search-keyword').keydown(function(key){
                if(key.keyCode==13){
                    let keyword = $('#input-search-keyword').val();
                    keyword = keyword.trim();
                    if(keyword==""){
                        alert("검색어를 입력하세요");
                        return
                    }
                    location.href="../board/list.jsp?keyword="+keyword;
                }
            });

        })
</script>
<header>
    <aside>
        <h1><a href="../board/list.jsp">로고</a></h1>
    </aside>
    <section>
        <article>
            <div id="search">
                <div>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <g clip-path="url(#clip0_42_1634)">
                            <path d="M15.5 14H14.71L14.43 13.73C15.41 12.59 16 11.11 16 9.5C16 5.91 13.09 3 9.5 3C5.91 3 3 5.91 3 9.5C3 13.09 5.91 16 9.5 16C11.11 16 12.59 15.41 13.73 14.43L14 14.71V15.5L19 20.49L20.49 19L15.5 14ZM9.5 14C7.01 14 5 11.99 5 9.5C5 7.01 7.01 5 9.5 5C11.99 5 14 7.01 14 9.5C14 11.99 11.99 14 9.5 14Z"
                                  fill="#8E8E95"/>
                        </g>
                        <defs>
                            <clipPath id="clip0_42_1634">
                                <rect width="24" height="24" fill="white"/>
                            </clipPath>
                        </defs>
                    </svg>
                    <label>
                        <input id="input-search-keyword" type="text" placeholder="검색어를 입력해주세요.">
                    </label>
                </div>
            </div>
            <div id="next-search"></div>
            <div id="login">
                <a href="../user/login.jsp">로그인</a>
            </div>
            <div id="signup">
                <a href="../user/signup.jsp">회원가입</a>
            </div>
        </article>
        <nav>
            <ul>
                <li>
                    <a href="../board/list.jsp">List</a>
                    <div class="under-bar"></div>
                </li>
                <li>
                    <a href="../board/write.jsp">write</a>
                    <div class="under-bar"></div>
                </li>
                <!-- <li>
                    <a href="#"></a>
                    <div class="under-bar"></div>
                </li> -->
                <!-- <li>
                    <a href="#">BOARD3</a>
                    <div class="under-bar"></div>
                </li> -->
            </ul>
        </nav>
    </section>
</header>