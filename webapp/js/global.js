$(function() {

    beUrl="http://localhost:8080/web_jsp";

    const userToken = $.cookie('userSession');
    let userInfo = window.localStorage.getItem(userToken);
    userSeq = 1;
    userNickname = "testNick";
    // if(userToken!=undefined){
    //     if(userInfo!=null){
    //         userInfo = JSON.parse(userInfo);
    //         userSeq = userInfo.userSeq;
    //         userNickname = userInfo.userNickname;
    //     }else{
    //         userSeq=null;
    //         userNickname=null;
    //     }
    // }else{
    //     userSeq=null;
    //     userNickname=null;
    // }

})

// header
$(function(){
    // 로그 아웃 상태
    if(userSeq==null){
        $('#login > a').text("로그인")
        $('#login > a').attr('href','../user/login.html');
        
        $('#signup > a').text("회원가입")
        $('#signup > a').attr('href','../user/signup.html');
    } else {
        // 로그 인 상태
        $('#login > a').text("로그아웃");
        $('#login > a').click(function(){
            const userToken = $.cookie('userSession');
            window.localStorage.removeItem(userToken);
            $.getJSON(beUrl+'/v1/user/logout',function(data){
                $.removeCookie('userSession',{path:'/'});
                location.replace('../user/login.html');
            })
        });
        
        $('#signup > a').text("마이페이지");
        $('#signup > a').attr('href','../user/edit_pw.html');
    }
})