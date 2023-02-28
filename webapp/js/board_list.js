const success = 'SUCCESS';

let page = 1;
let size = 12;
$(function() {
    $('.btn').css({"text-decoration":"none"})
    $.getJSON('../v1/board?page='+page+'&size='+size, function(result){
        if(result.status===success){
            let data = result.data;
            $(data).each(function(idx,brd){
                $('<tr/>')
                .append($('<td/>').html(brd.seq))
                .append($('<td/>').html(brd.title))
                .append($('<td/>').html(brd.nickname))
                .append($('<td/>').html(brd.date))
                .append($('<td/>').html(brd.viewCnt))
                .append($('<td/>').html(brd.likeCnt))
                .css({'cursor': 'pointer'})
                .click(function(){
                    location.href="detail.jsp?brdSeq="+brd.seq;
                })
            .appendTo('.tbody-list')
            })
        } else {
            alert("서비스 오류! 관리자에게 문의하세요(code:bl1)");
        }
    });
})