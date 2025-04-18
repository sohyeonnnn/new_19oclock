$(document).ready(function () {
    //submit 버튼 비활성화
    $("input:submit").prop("disabled", true);
    function blurEvt($inputTarget, $validationTarget) {
        if ($inputTarget.val() != '')
            $inputTarget.trigger("keyup");
        else {
            $inputTarget.removeAttr("style");
            $validationTarget.hide();
        }
    }
    //pw 정규식
    $("#pw1").on("keyup", (function (e) {
        let regExp = /^[a-zA-z0-9_!@#$%^]{4,}$/;
        if (regExp.exec($(this).val())) {
            console.log("비번조건 통과함: " + $(this).val())
            $("#pw1_validation").hide();
            $(this).removeAttr("style");
        } else {
            $("#pw1_validation").show();
            $(this).css("border-color", "red");
        }
        checkAllValidation();
    }));
    $("#pw1").on("blur", function () {
        blurEvt($(this), $("#pw1_validation"));
        blurEvt($("#pw2"), $("#pw2_validation"));
    });
    //pw 재확인
    $("#pw2").on("keyup", (function (e) {
        if ($("#pw1").val() == $(this).val()) {
            console.log("비번 확인조건 통과함: " + $(this).val())
            $("#pw2_validation").hide();
            $(this).removeAttr("style");
        } else {
            $("#pw2_validation").show();
            $(this).css("border-color", "red");
        }
        checkAllValidation();
    }));
    $("#pw2").on("blur", function () {
        blurEvt($(this), $("#pw2_validation"));
    });
    //모든 유효성 검사가 완료되었을 시 버튼 클릭 가능하게 바꿈.
    function checkAllValidation() {
        if ($("#pw1").val() == '' || $("#pw2").val() == '') {
            $("input:submit").prop("disabled", true);
            $("input:submit").removeAttr("style");
            console.log("if");
        } else if ($("#pw1_validation").css("display") == "block" ||
            $("#pw2_validation").css("display") == "block") {
            $("input:submit").prop("disabled", true);
            $("input:submit").removeAttr("style");
            console.log("elseif");
        } else {
            $("input:submit").prop("disabled", false);
            $("input:submit").css("background-color", "#314C83");
            $("input:submit").css("color", "white");
            $("input:submit").css("cursor", "pointer");

            console.log("else");
        }

    }
});