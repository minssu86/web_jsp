const success = 'SUCCESS';

$(function(){
     // 비회원 접근 방지
     if(userSeq==null){
        alert("로그인이 필요한 서비스입니다.");
        location.href='../user/login.html';
    }
    let urlstr = window.location.href;
    let url = new URL(urlstr);
    let brdSeq = url.searchParams.get("brdSeq");
    $.getJSON(beUrl+'/v1/board/detail?brdSeq='+brdSeq, function(result){
        if(result.status===success){
            let data = result.data;
            $('#write-title').val(data.title);
            $('#textarea-edit-content').text(data.content);
        } else {
            alert("서비스 오류! 관리자에게 문의하세요(code:be1)");
        }
    });

    $('#btn-edit-board').click(function(){
        let title = $('#write-title').val();
        let content = $('#textarea-edit-content').val();

        if(title==''){
            alert('제목을 입력하세요!');
            return;
        } 
        if(content==''){
            alert('내용을 입력하세요!');
            return;
        }
        data = {
            "title" : title,
            "content" : content
        };
        $.ajax({
            url : beUrl+'/v1/board/edit?brdSeq='+brdSeq,
            type : 'post',
            data : data,
            dataType : "json",
            success : function(result){
                if(result.status==success){
                  location.href="detail.html?brdSeq="+brdSeq;
                } else {
                    alert("오류 발생! 관리자에게 문의 하세요(code:bw1)");
                }
            }
        });
    });

})