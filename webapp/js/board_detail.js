const success = 'SUCCESS';

$(function(){
    $('#btn-delete-board').css({'z-index':'6','position':'relative','visibility':'hidden'});
    $('#btn-edit-board').css({'z-index':'6','position':'relative','visibility':'hidden'});
    $('#btn-is-like').css({'cursor':'pointer'});
    let urlstr = window.location.href;
    let url = new URL(urlstr);
    let brdSeq = url.searchParams.get("brdSeq");

    // 상세 정보 요청
    $.getJSON('../v1/board/detail?brdSeq='+brdSeq, function(result){
        if(result.status===success){
            let data = result.data;
            $('#h2-print-title').text(data.title);
            $('#div-print-nickname').text(data.nickname);
            $('#div-print-date').text(data.date);
            $('#h3-print-comment-count').text(data.commentCnt);
            $('#h3-print-like-count').text(data.likeCnt);
            $('#h3-print-view-count').text(data.viewCnt);
            $('#p-print-content').text(data.content);

            if(userNickname===data.nickname){
                $('#btn-delete-board').css({'z-index':'6','position':'relative','visibility':'visible'});
                $('#btn-edit-board').css({'z-index':'6','position':'relative','visibility':'visible'});
            }
            changeLikeStatus(data.isLiked);

        } else {
            alert("서비스 오류! 관리자에게 문의하세요(code:be1)");
        }
    });

    // 추천
    $('#btn-is-like').click(function(){
        if(userNickname===null){
            alert("로그인이 필요한 기능입니다.");
            return;
        }
        $.ajax({
            url : '../v1/board/like?brdSeq='+brdSeq,
            type : 'post',
            dataType : "json",
            success : function(result){
                if(result.status==success){
                    $('#h3-print-like-count').text(result.data.likeCnt);
                    changeLikeStatus(result.data.isLiked);
                } else {
                    alert("오류 발생! 관리자에게 문의 하세요(code:bw1)");
                }
            }
        });
    });

    let changeLikeStatus = function(isLiked){
        if(isLiked){
            $('#svg-like-true').css({'display':'inline'});
            $('#svg-like-false').css({'display':'none'});
            $('#span-print-islike').css({'color':'#ED7474'});
        }else{
            $('#svg-like-true').css({'display':'none'});
            $('#svg-like-false').css({'display':'inline'});
            $('#span-print-islike').css({'color':'#1E1E20'});
        }
    };

    // 수정 페이지 이동
    $('#btn-edit-board').click(function(){
        location.href="edit.jsp?brdSeq="+brdSeq;
    });

    // 게시글 삭제
    $('#btn-delete-board').click(function(){
        $.ajax({
            url : '../v1/board?brdSeq='+brdSeq,
            type : 'delete',
            dataType : "json",
            success : function(result){
                if(result.status==success){
                    alert("게시글 삭제 성공");
                    location.href="../board/list.jsp";
                } else {
                    alert("오류 발생! 관리자에게 문의 하세요(code:bw1)");
                }
            }
        });
    });

});