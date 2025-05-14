$(document).ready(function () {
    $("#btnPw").on("click", function () {
        $("#mIdHidden").val($("#mId").val());
        let number = $("#mPhone2").val();
        $("#mPhoneHidden").val(number);
        //let left = Math.ceil((window.screen.width-400)/2);
        //let top = Math.ceil((window.screen.height-350)/2);
        //window.open('', 'popup', 'width=500px, height=350px, left='+left+', top='+top)
        $("#pop-form").submit();
    });
    $("form").submit(function (e) {
        let number = $(this).children("input[name=phoneNo]").val();
        let phone = number.substring(0,3)+'-'+number.substring(3,7)+'-'+number.substring(7,11);
        $(this).children("input[name=phoneNo]").val(phone);
    });
});