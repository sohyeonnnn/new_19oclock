

$(document).ready(function () {
    // submit 버튼 비활성화
    $(".submit>button").prop("disabled", true);

    // ID 정규식
    $(".id-form input").on("keyup", function () {
        let regExp = /^\w{3,20}$/;
        if (regExp.exec($(this).val())) {
            $("#id_validation").hide();
            $(this).removeAttr("style");
        } else {
            $("#id_validation").show();
            $(this).css("border-color", "red");
        }
        checkAllValidation();
    });

    $(".id-form input").on("blur", function () {
        blurEvt($(this), $("#id_validation"));
    });

    // 비밀번호 정규식
    $(".pw-form #pw1").on("keyup", function () {
        let regExp = /^[a-zA-Z0-9_!@#$%^]{4,}$/;
        if (regExp.exec($(this).val())) {
            $("#pw1_validation").hide();
            $(this).removeAttr("style");
        } else {
            $("#pw1_validation").show();
            $(this).css("border-color", "red");
        }
        checkAllValidation();
    });

    $(".pw-form #pw1").on("blur", function () {
        blurEvt($(this), $("#pw1_validation"));
        blurEvt($(".pw-form #pw2"), $("#pw2_validation"));
    });

    // 비밀번호 재확인
    $(".pw-form #pw2").on("keyup", function () {
        if ($(".pw-form #pw1").val() == $(this).val()) {
            $("#pw2_validation").hide();
            $(this).removeAttr("style");
        } else {
            $("#pw2_validation").show();
            $(this).css("border-color", "red");
        }
        checkAllValidation();
    });

    $(".pw-form #pw2").on("blur", function () {
        blurEvt($(this), $("#pw2_validation"));
    });

    // 이름 정규식
    $(".name-form input").on("keyup", function () {
        let regExp = /^[가-힣]{1,6}$/;
        if (regExp.exec($(this).val())) {
            $("#name_validation").hide();
            $(this).removeAttr("style");
        } else {
            $("#name_validation").show();
            $(this).css("border-color", "red");
        }
        checkAllValidation();
    });

    $(".name-form input").on("blur", function () {
        blurEvt($(this), $("#name_validation"));
    });

    // 이메일 정규식
    $(".email-form input").on("keyup", function () {
        let regExp = /^\w+@(\w+\.\w+){1,}$/;
        if ($(this).val().length > 30) {
            $("#email_validation").show();
            $(this).css("border-color", "red");
        } else if (regExp.exec($(this).val())) {
            $("#email_validation").hide();
            $(this).removeAttr("style");
        } else {
            $("#email_validation").show();
            $(this).css("border-color", "red");
        }
        checkAllValidation();
    });

    $(".email-form input").on("blur", function () {
        blurEvt($(this), $("#email_validation"));
    });

    // 전화번호 정규식
    $(".phone-form input").on("keyup", function () {
        let valid = true;
        $.each($(".phone-form input:text"), function (index, value) {
            let regExp1 = /^[0-9]{3}$/;
            let regExp2 = /^[0-9]{4}$/;
            if (index == 0 && regExp1.exec($(value).val())) {
            } else if ((index == 1 || index == 2) && regExp2.exec($(value).val())) {
            } else {
                valid = false;
            }
        });

        if (valid) {
            $("#phone_validation").hide();
            $(".phone-form input").removeAttr("style");
            let phoneStr = $("#phone1").val() + "-" + $("#phone2").val() + "-" + $("#phone3").val();
            $("[name=mPhone]").val(phoneStr);
        } else {
            $("#phone_validation").show();
            $(".phone-form input").css("border-color", "red");
        }
        checkAllValidation();
    });

    $(".phone-form input").on("blur", function () {
        $(".phone-form input:text").each(function () {
            blurEvt($(this), $("#phone_validation"));
        });
    });

    // 약관 동의
    $("#allchk").on("change", function () {
        let checked = $(this).prop("checked");
        $("#service").prop("checked", checked);
        $("#privacy").prop("checked", checked);
        checkAllValidation();
    });

    $("#service, #privacy").on("change", function () {
        if ($("#service").prop("checked") && $("#privacy").prop("checked")) {
            $("#allchk").prop("checked", true);
        } else {
            $("#allchk").prop("checked", false);
        }
        checkAllValidation();
    });

    // 스크롤 막기
    function blockScroll(toggle) {
        if (toggle) {
            $("body").css('height', '100vh');
            $("body").css('overflow', 'hidden');
        } else {
            $("body").removeAttr('style');
        }
    }

    // 약관 버튼 클릭 시 모달창 열기
    $(".terms-bottom label input[type=button]:eq(0)").on("click", function () {
        $(".background-screen").show().css("top", window.scrollY);
        $(".service-terms-container").show();
        blockScroll(true);
    });

    $(".terms-bottom label input[type=button]:eq(1)").on("click", function () {
        $(".background-screen").show().css("top", window.scrollY);
        $(".privacy-terms-container").show();
        blockScroll(true);
    });

    // 배경 클릭 시 닫기
    $(".background-screen, .terms-close").on("click", function () {
        $(".background-screen").hide();
        $(".service-terms-container").hide();
        $(".privacy-terms-container").hide();
        blockScroll(false);
    });

    // 모든 조건 만족 시 버튼 활성화
    function checkAllValidation() {
        if (
            $(".id-form input").val() == '' ||
            $(".pw-form #pw1").val() == '' ||
            $(".pw-form #pw2").val() == '' ||
            $(".name-form input").val() == '' ||
            $(".email-form input").val() == '' ||
            $("#phone1").val() == '' ||
            $("#phone2").val() == '' ||
            $("#phone3").val() == ''
        ) {
            $(".submit>button").prop("disabled", true).removeAttr("style");
        } else if (
            $("#id_validation").is(":visible") ||
            $("#pw1_validation").is(":visible") ||
            $("#pw2_validation").is(":visible") ||
            $("#name_validation").is(":visible") ||
            $("#email_validation").is(":visible") ||
            $("#phone_validation").is(":visible")
        ) {
            $(".submit>button").prop("disabled", true).removeAttr("style");
        } else if (!$("#service").prop("checked") || !$("#privacy").prop("checked")) {
            $(".submit>button").prop("disabled", true).removeAttr("style");
        } else {
            $(".submit>button")
                .prop("disabled", false)
                .css({ "background-color": "#314C83", "color": "white", "cursor": "pointer" });
        }
    }

    // 아이디 중복 검사
    $("#reg-form").on("submit", function (e) {
        e.preventDefault(); // 가장 먼저 위치해야 함
        $.ajax({
            type: "post",
            url: "/checkDupId",
            data: {
                id: $("#id").val()
            },
            dataType: "json",
            success: function (response) {
                console.log(response);
                if (response.isSuccess) {
                    $("#id").val('');
                    $("#id").trigger("keyup");
                    $("#id").trigger("blur");
                } else {
                    // 아이디 중복이 없으면 실제 submit 처리
                    $("#reg-form")[0].submit();
                }
            },
            error: function (response) {
                alert("서버 오류가 발생했습니다.");
            }
        });
    });
});
function blurEvt($inputTarget, $validationTarget) {
    if ($inputTarget.val() != '') {
        $inputTarget.trigger("keyup");
    } else {
        $inputTarget.removeAttr("style");
        $validationTarget.hide();
    }
}