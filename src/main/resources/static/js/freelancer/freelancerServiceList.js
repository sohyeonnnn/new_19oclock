$(document).ready(function () {
    // 메뉴 고정
    $(".menu").children().eq(2).find('a').css({'margin-left': '5px', 'font-weight': 'bold'});
    $(".menu").children().eq(2).find('img').css({'display': 'inline'});

    var order =  $("#order").val();

    // 선택된 값 유지
    if (order === "approved" || order === "rejected") {
        $(".array").val(order);
    }

    // 값 변경 시 페이지 이동
    $(".array").change(function () {
        var order = $(this).val();
        location.href = "/freelancerServiceList?order=" + order;
    });

});

// 삭제 함수 정의
function del() {
    var sNo = $("#sNo").val();
    if (confirm("해당 서비스를 삭제할까요?")) {
        location.href = "/delService?serviceNo=" + sNo;
    }
}
