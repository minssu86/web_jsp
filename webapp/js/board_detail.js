$(function(){
    $('#btn-delete-board').css({'z-index':'6','position':'relative'});
    let urlstr = window.location.href;
    let url = new URL(urlstr);
    let brdSeq = url.searchParams.get("brdSeq");
  
    // $.getJSON('../v1/board/'+brdSeq, function(result){
    //     if(result.status===success){
    //         let data = result.data;
    //         $('#write-title').text(data.brdTitle);
    //         $('#textarea-edit-content').text(data.brdContent);
    //     } else {
    //         alert("서비스 오류! 관리자에게 문의하세요(code:be1)");
    //     }
    // });

    $('#btn-edit-board').click(function(){

    });
    $('#btn-delete-board').click(function(){
        let formData = {
            "brdSeq" : brdSeq
        }
        console.log("???");
        $.ajax({
            url : '../v1/board',
            type : 'delete',
            data : formData,
            dataType : "json",
            success : function(result){
                if(result.status==sucess){
                    alert("게시글 삭제 성공");
                    location.href="../board/list.jsp";
                } else {
                    alert("오류 발생! 관리자에게 문의 하세요(code:bw1)");
                }
            }
        });
    });

});