
$(function(){
     // 비회원 접근 방지
     if(userSeq==null){
        alert("로그인이 필요한 서비스입니다.");
        location.href='../user/login.jsp';
    }
    let brdSeq = 10;

    $.getJSON('../v1/board/'+brdSeq, function(result){
        if(result.status===success){
            let data = result.data;
            $('#write-title').text(data.brdTitle);
            $('#textarea-edit-content').text(data.brdContent);
        } else {
            alert("서비스 오류! 관리자에게 문의하세요(code:be1)");
        }
    });

})