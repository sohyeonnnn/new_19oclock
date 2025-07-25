document.addEventListener('DOMContentLoaded', function() {
    $('#email-check').hide();
    $('#phone-check').hide();

    //메뉴 고정
    $(".menu").children().eq(0).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
    $(".menu").children().eq(0).find('img').css({'display':'inline'});

    //폰번호 분리
    var phone_data = $("#phone").val();
    $("#phone1").val(phone_data.substring(4,8));
    $("#phone2").val(phone_data.substring(9));

    $("#modal_pwChange_close_btn").click(function(){
        $('#modal_pwChange').toggle('slow');
    });
    $("#modal_pwChange_confirm_btn").click(function() {
        var mId = $('#id-label').val();
        var mPw = $('#pw-label').val();
        var pwCheck1 = $("#pw-check1").val();
        var pwCheck2 = $("#pw-check2").val();
        var regCheck = $("#reg-check").val();
        var object = 'pw';

        if (pwCheck1 === pwCheck2 && pwCheck2 !== '' && regCheck === 'true') {
            $.post("/updatePassword", {
                memberId: mId,
                memberPw: mPw,
                data: pwCheck2,
                object: object
            })
                .done(function(response) {
                    alert("비밀번호가 변경되었습니다.");
                    location.href = "/userMypage";
                })
                .fail(function() {
                    alert("비밀번호 변경 실패");
                });
        } else {
            alert("다시 한 번 확인해주세요");
        }
    });
    $("#pw-current").keyup(function(){
        var mNo = $('#mNo').val();
        var mId = $('#mId').val();
        var mPw_input = $('#pw-current').val();
        var mPw_current = $('#pw-label').val();
        $.ajax({
            url: '/checkPassword',
            type: 'POST',
            data: { memberId: mId, memberPw: mPw_input, memberNo : mNo },
            success: function (result) {
                if (result.success) {
                    // 서버에서 비밀번호 인증 성공 시
                    $('#pw-current').attr('readonly', true).css({'background-color': 'rgba(224, 224, 224, 0.4)'});
                    $('#pw-current').next().children().css({'color':'#008000'}).html('확인되셨습니다. 변경할 비밀번호를 입력해주세요').fadeIn(1000).delay(1000).fadeOut(1000);
                    $('#pw-check1').attr('readonly', false).css({'background-color': 'white'}).focus()
                    $('#pw-check2').attr('readonly', false).css({'background-color': 'white'});
                } else {
                    $('#pw-current').next().children().css({'color':'red'}).html('올바른 비밀번호를 입력해주세요');
                }
            },
            error: function () {
                alert('서버 오류가 발생했습니다. 다시 시도해주세요.');
            }
        });

        /*    if(mPw_input == mPw_current){
                $('#pw-current').attr('readonly', true).css({'background-color': 'rgba(224, 224, 224, 0.4)'});
                $('#pw-current').next().children().css({'color':'#008000'}).html('확인되셨습니다. 변경할 비밀번호를 입력해주세요').fadeIn(1000).delay(1000).fadeOut(1000);
                $('#pw-check1').attr('readonly', false).css({'background-color': 'white'}).focus()
                $('#pw-check2').attr('readonly', false).css({'background-color': 'white'});
            }else{
                $('#pw-current').next().children().css({'color':'red'}).html('올바른 비밀번호를 입력해주세요');
            }*/
    });
    $("#pw-check1").keyup(function(){
        var pwCheck1 = $("#pw-check1").val();
        var reg = /^(?=.*?[A-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{4,}$/.test(pwCheck1);
        if(reg){
            $('#reg-check').val('true');
            $('#pw-check1').next().children().css({'color':'#008000'}).html('조건을 만족하였습니다').delay(1000).fadeOut(1000);
        }else if(!reg && pwCheck1 != ''){
            $('#reg-check').val('false');
            $('#pw-check1').next().children().css({'color':'red'}).html('비밀번호 조건을 만족해주세요').show();

        }
    });
    $("#pw-check2").keyup(function(){
        var pwCheck1 = $("#pw-check1").val();
        var pwCheck2 = $("#pw-check2").val();
        if(pwCheck1 == pwCheck2 && pwCheck2 != ''){
            $('#pw-check2').next().children().css({'color':'#008000'}).html('일치합니다').fadeOut(1000);
        }else if(pwCheck1 != pwCheck2 && pwCheck2 != ''){
            $('#pw-check2').next().children().css({'color':'red'}).html('일치하지 않습니다').show();
        }
    });


});

//이메일 수정하기
function emailEdit(obj){
    var mNo = $('#no-label').val();
    var mId = $('#id-label').val();
    var status = $('#email-btn').html();
    var mEmail = $('#email-label').val();
    if(status == "수정"){
        $('#email-label').attr('readonly', false).css({'background-color': 'white'});
        $('#email-btn').html('저장');
    }else{
        $.ajax({
            url : "/updateInfo",
            type : "get",
            data : {"memberNo":mNo, "data":mEmail,"object":"email" },
            success : function(data){
                $('#email-label').attr('readonly', true).css({'background-color': 'rgba(224, 224, 224, 0.4)'});
                $('#email-btn').html("수정");
                $('#email-check').fadeIn(1000).delay(1000).fadeOut(1000);
            },
            error : function(){
            }
        });
    }
}

//전화번호 수정하기 누르면
function phoneEdit(obj){
    var mNo = $('#no-label').val();
    var mId = $('#id-label').val();
    var status = $('#phone-btn').html();
    var phone1 = $("#phone1").val();
    var phone2 = $("#phone2").val();
    if(status == "수정"){
        $('#phone1').attr('readonly', false).css({'background-color': 'white'});
        $('#phone2').attr('readonly', false).css({'background-color': 'white'});
        $('#phone-btn').html('저장');
    }else{
        var phone = "010-"+phone1+"-"+phone2;
        var reg = /^[0-9]{4}$/;
        if(reg.test(phone1) && reg.test(phone2)){
            $.ajax({
                url : "/updateInfo",
                type : "get",
                data : {"memberNo":mNo, "memberId":mId,"data":phone,"object":"phone" },
                success : function(data){
                    $('#phone1').attr('readonly', true).css({'background-color': 'rgba(224, 224, 224, 0.4)'});
                    $('#phone2').attr('readonly', true).css({'background-color': 'rgba(224, 224, 224, 0.4)'});
                    $('#phone-btn').html("수정");
                    $('#phone-check').html('정상적으로 수정되었습니다').css({'color':'#008000'}).fadeIn(1000).delay(1000).fadeOut(1000);
                },
                error : function(){
                }
            });
        }else{
            $('#phone-check').html('숫자 4자리로 작성해주세요').css({'color':'red'}).fadeIn(1000).delay(1000).fadeOut(1000);
        }
    }
}

//비밀번호 변경하기
function pwChange(){
    $('#modal_pwChange').toggle('slow');
}


//회원탈퇴 하기
function deleteMember() {
    $('#modal').toggle('slow');

    $("#modal_close_btn").off('click').on('click', function () {
        $('#modal').toggle('slow');
    });

    $("#modal_confirm_btn").off('click').on('click', function () {
        var mNo = $('#mNo').val();
        var mId = $('#id-label').val();
        var mPw_check = $('#pw-check').val();

        $.ajax({
            url: '/checkPassword',
            type: 'POST',
            data: { memberId: mId, memberPw: mPw_check, memberNo : mNo },
            success: function (result) {
                if (result.success) {
                    // 서버에서 비밀번호 인증 성공 시
                    location.href = "/deleteMember?memberNo=" + mNo;
                } else {
                    alert('비밀번호가 일치하지 않습니다. 다시 입력해주세요.');
                }
            },
            error: function () {
                alert('서버 오류가 발생했습니다. 다시 시도해주세요.');
            }
        });
    });
}