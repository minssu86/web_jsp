const sucess = 'SUCCESS';
$(function(){

    $('#btn-login').click(function(){

        let id = $('#input-id').val();
        let password = $('#input-password').val();

        let passwordCheckRegex = /^[A-Za-z0-9!@#$%]{8,20}$/;

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
                success : function(data){
                    if(data.status===sucess){
                        alert("로그인 성공! 환영합니다!");
                        location.href="../board/list.jsp";
                    }else{
                        alert("로그인 실패! 입력 확인 바랍니다");
                        return;
                    }
                    console.log(data.status);
                }
            });

        } else {
            alert('비밀번호 형식이 맞지 않습니다');
        }

    });

});