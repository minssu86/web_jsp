const success = 'SUCCESS';

$(function() {

    $('#svg-last-page').css({'display':'none'});
    $('#svg-after10-page').css({'display':'none'});
    $('#svg-first-page').css({'display':'none'});
    $('#svg-before10-page').css({'display':'none'});

    let urlstr = window.location.href;
    let url = new URL(urlstr);
    let keyword = url.searchParams.get("keyword");

    // 한 페이지당 게시글 갯수
    let boardCntPerPage = 12;
    let totalPage;
    let currentPage=1;
    let pageCntPerGroup = 10;

    $('.btn').css({"text-decoration":"none"})
    let loadPage = function(page, size){

        let requestUrl;
        if(keyword==undefined){
            requestUrl = '../v1/board?page='+page+'&size='+size;
        }else{
            requestUrl = '../v1/board?page='+page+'&size='+size+'&keyword='+keyword;
        }

        $.getJSON(requestUrl, function(result){
            if(result.status===success){
                let data = result.data;
                $(".tbody-list").empty();
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
                //페이지네이션 설정
                renderPagination(page,result.totalBoardCount);
            } else {
                renderPagination(0,0);
                alert("일치하는 게시글이 없습니다");
            }
        });
    };


    let renderPagination = function(page, totalBoardCount){
        currentPage=page;
        // 페이지 표현 범위
        // 전체 게시물이 한페이지로 커버 가능하기에 pagination 숨김
        if(totalBoardCount <= boardCntPerPage) {
            $('.svg-page').css({'display':'none'});
            return;
        }
        $('.svg-page').css({'display':'inline'});
        // 전체 페이지 갯수 Math.ceil 올림 계산
        totalPage = Math.ceil(totalBoardCount/boardCntPerPage);
        let pageGroup = Math.ceil(page/pageCntPerGroup);
        // 현재 화면 마지막 페이지 번호
        let endPage = pageGroup * pageCntPerGroup;
        if(endPage > totalPage) {
            endPage = totalPage;
            $('#svg-last-page').css({'display':'none'});
            $('#svg-after10-page').css({'display':'none'});
        
        }else{
            $('#svg-last-page').css({'display':'inline'});
            $('#svg-after10-page').css({'display':'inline'});
        }
        // 현재 화면 첫번째 페이지 번호
        let startPage = endPage <=(pageCntPerGroup)? 1: endPage - (endPage-1)%pageCntPerGroup;

        if(startPage===1){
            $('#svg-first-page').css({'display':'none'});
            $('#svg-before10-page').css({'display':'none'});
        }else{
            $('#svg-first-page').css({'display':'inline'});
            $('#svg-before10-page').css({'display':'inline'});
        }

        // 출력
        $("#span-print-paging").empty();
        for(let i=startPage; i<endPage+1; i++){
            if(currentPage===i){
                $('<li/>').text(i).appendTo('#span-print-paging')
                .css({"cursor":"pointer",
                "background-color":"#804C4C",
                "color":"#FFFFFF", 
                "border": "1px solid #EAEAEC",
                "border-radius":"16px"})
                .click(function(){
                    currentPage=i;
                    loadPage(currentPage, boardCntPerPage);
                });
            }else{
                $('<li/>').text(i).appendTo('#span-print-paging')
                .css({"cursor":"pointer"})
                .click(function(){
                    currentPage=i;
                    loadPage(currentPage, boardCntPerPage);
                });
            }
        }
    };

    // 최초 페이지 정보 호출
    loadPage(currentPage, boardCntPerPage);

    // 첫페이지로 이동
    $('#svg-first-page').click(function(){
        loadPage(1, boardCntPerPage);
    });

    // 마지막 페이지로 이동
    $('#svg-last-page').click(function(){
        loadPage(totalPage, boardCntPerPage);
    });

    // 10 페이지 앞으로
    $('#svg-before10-page').click(function(){
        loadPage(currentPage-10, boardCntPerPage);
    });

    // 10 페이지 뒤로
    $('#svg-after10-page').click(function(){
        loadPage(currentPage+10, boardCntPerPage);
    });

})