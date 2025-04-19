$(document).ready(function () {
    //카테고리 목록 불러오는거
    $.ajax({
        url: '/categoryLoad',
        dataType: 'json',
        success: function (data) {
            let $navUl = $(".nav>ul");

            for (let i = 0; i < data.length; i++) {
                if (data[i].cNo == data[i].cDivisionNo ) {
                    $navUl.append("<li><a href='/serviceList?cNo=" + data[i].cNo +
                        "&reqPage=1&order=new&keyword='>" + data[i].cName +
                        "</a> <ul></ul></li>")

                }else{
                    $(".nav>ul>li:eq(" + (parseInt(data[i].cNo / 10) - 1) + ")>ul").append(
                        "<li><a href='/serviceList?cNo=" + data[i].cNo +
                        "&reqPage=1&order=new&keyword='>" + data[i].cName + "</a></li>")
                }
            }
        }
    })
    //스크롤 막기 true : 막기 , false : 풀기
    function blockScroll(toggle) {
        if (toggle) {
            $("body").css('height', '100vh');
            $("body").css('overflow', 'hidden');
        } else {
            $("body").removeAttr('style');
        }
    }

    //헤더의 로그인 버튼 누르면 로그인 창 show
    $(".header-menu #login").on("click", function () {
        $(".background-screen").show();
        $(".background-screen").css("top", window.scrollY);
        $(".login-form-container").show();
        blockScroll(true);
    });
    // 뒷 배경 누르면 ,로그인 창, 뒷 배경 Hide
    $(".background-screen").on("click", function () {
        $(".background-screen").hide();
        $(".login-form-container").hide();
        blockScroll(false);
    });
    // 로그인 창 x버튼 누르면 ,로그인 창, 뒷 배경 Hide
    $(".login-form-close").on("click", function () {
        $(".background-screen").hide();
        $(".login-form-container").hide();
        blockScroll(false);
    });
    //submit 버튼 비활성화
    $("#btnLogin").prop("disabled", true);

    function blurEvt($inputTarget, $validationTarget) {
        if ($inputTarget.val() != '')
            $inputTarget.trigger("keyup");
        else {
            $inputTarget.removeAttr("style");
            $validationTarget.hide();
        }
    }
    let idAllowed = false;
    //id 정규식
    $("#id").on("keyup", (function (e) {
        // m_id             VARCHAR2(20)
        let regExp = /^\w{3,20}$/;
        if (regExp.exec($(this).val())) {
            $("#id_validation").hide();
            $(this).removeAttr("style");
            idAllowed = true;
        } else {
            $("#id_validation").show();
            $(this).css("border-color", "red");
            idAllowed = false;
        }
        checkAllValidation();
    }));
    $("#id").on("blur", function () {
        blurEvt($(this), $("#id_validation"));
    });
    let pwAllowed = false;
    //pw 정규식
    $("#pw").on("keyup", (function (e) {
        let regExp = /^[a-zA-z0-9_!@#$%^]{4,}$/;
        if (regExp.exec($(this).val())) {
            $("#pw1_validation").hide();
            $(this).removeAttr("style");
            pwAllowed = true;
        } else {
            $("#pw1_validation").show();
            $(this).css("border-color", "red");
            pwAllowed = false;
        }
        checkAllValidation();
    }));
    $("#pw").on("blur", function () {
        blurEvt($(this), $("#pw1_validation"));
    });

    //모든 유효성 검사가 완료되었을 시 버튼 클릭 가능하게 바꿈.
    function checkAllValidation() {
        if ($("#id").val() == '' || $("#pw").val() == '') {
            $("#btnLogin").prop("disabled", true);
            $("#btnLogin").removeAttr("style");
        } else if (idAllowed == false || pwAllowed == false) {
            $("#btnLogin").prop("disabled", true);
            $("#btnLogin").removeAttr("style");
        } else {
            $("#btnLogin").prop("disabled", false);
            $("#btnLogin").css("background-color", "#314C83");
            $("#btnLogin").css("color", "white");
            $("#btnLogin").css("cursor", "pointer");
        }

    }
    $("#user_name").on("hover", function (e) {
        $(".ul-user").css("display", "block");
    })
    $(".ul-user").on("hover", function (e) {
        $(".ul-user").css("display", "block");
    })

    // 로그인 후 원래 있던 페이지로 돌아가기 위한 코드
    $("#login-loc").val(location.href);
});

