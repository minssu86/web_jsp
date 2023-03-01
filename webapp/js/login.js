const success = 'SUCCESS';

$(function(){

    $('#btn-login').click(function(){

        let id = $('#input-id').val();
        let password = $('#input-password').val();

        let passwordCheckRegex = /^[A-Za-z0-9!@#$%]{4,20}$/;

        if(id==''){
            alert("아이디를 입력해주세요");
        }else if(passwordCheckRegex.exec(password)){
            
            let formData = {
                "id" : id,
                "password" : password,
            }

            $.ajax({
                url : '../v1/user/login',
                type : 'post',
                data : formData,
                dataType : "json",
                success : function(result){
                    if(result.status===success){
                        window.localStorage.clear();
                        let userToken = $.cookie('userSession');
                        let userInfo = new Object;
                        userInfo.userSeq = result.data.userSeq;
                        userInfo.userNickname = result.data.userNickname;
                        userInfo = JSON.stringify(userInfo);
                        window.localStorage.setItem(userToken,userInfo)
                        alert("로그인 성공! 환영합니다!");
                        location.href="../board/list.jsp";
                    }else{
                        alert("로그인 실패! 입력 확인 바랍니다");
                        return;
                    }
                }
            });

        } else {
            alert('비밀번호 형식이 맞지 않습니다');
        }

    });

});