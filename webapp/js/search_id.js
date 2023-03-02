const success = 'SUCCESS';
$(function(){

    $('#btn-request-code').click(function(){

        let name = $('#search-id-name').val();
        let email = $('#search-id-email').val();
        console.log(email);
        $.getJSON(beUrl+'/v1/user/id-check?name='+name+'&email='+email,function(data){
            let result = data.status;
            if(result==success){
                alert("email 인증번호 확인 바랍니다")
            } else {
                alert("입력 정보 확인 바랍니다")
            }
        });

    })

    $('#btn-send-formdata').click(function(){

        let name = $('#search-id-name').val();
        let email = $('#search-id-email').val();
        let checkcode = $('#search-id-code').val();

        if(name=='' || email=='' || checkcode==''){
            alert("빈 칸을 채워주세요");
        } else {
            let formData = {
                "name" : name,
                "email" : email,
                "checkcode" : checkcode
            }
            $.ajax({
                url : beUrl+'/v1/user/id-check',
                type : 'post',
                data : formData,
                dataType : "json",
                success : function(result){
                    if(result.status==success){
                        console.log("userId : "+result.data.userId);
                        console.log("date : "+result.data.createdAt);
                        alert("인증 완료");
                        location.href='search_id_result.html?id='+result.data.userId+"&date="+result.data.createdAt;
                    }else{
                        alert("인증 실패! 다시 시도해 주세요")
                    }
                }
            });
        }

    })

});