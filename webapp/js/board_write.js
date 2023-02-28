const success = 'SUCCESS';
$(function() {
    $('.btn').css({'cursor': 'pointer'});
    // 비회원 접근 방지
    if(userSeq==null){
        alert("로그인이 필요한 서비스입니다.");
        location.href='../user/login.jsp';
    }

    $('#btn-back').click(function(){
        $('#write-title').text("");
        $('#textarea-write-content').text("");
        history.back();
    });

    
    $('#btn-write-board').click(function(){

        let title = $('#write-title').val();
        let content = $('#textarea-write-content').val();

        if(title==''){
            alert('제목을 입력하세요!');
            return;
        } 
        if(content==''){
            alert('내용을 입력하세요!');
            return;
        }

        let formData = {
            "title" : title,
            "content" : content
        }

        $.ajax({
            url : '../v1/board',
            type : 'post',
            data : formData,
            dataType : "json",
            success : function(result){
                if(result.status==success){
                    alert("게시글 작성 완료");
                    location.href="../board/list.jsp";
                } else {
                    alert("오류 발생! 관리자에게 문의 하세요(code:bw1)");
                }
            }
        });

    });



})