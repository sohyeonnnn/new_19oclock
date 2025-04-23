$(document).ready(function () {
    // 배너 슬라이더 부분
    $('.banner-slider').slick({
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1,
        draggable: false,
        autoplay: true,
        autoplaySpeed: 5000,
        prevArrow: $("#arrow_prev"),
        nextArrow: $("#arrow_next")
    });
    // 슬라이더 부분
    $.each($('.famous-category-list'), function (index, valueOfElement) {
        $(valueOfElement).slick({
            infinite: true,
            slidesToShow: 5,
            slidesToScroll: 1,
            draggable: false,
            prevArrow: $("#arrow" + (index + 1) + "_prev"),
            nextArrow: $("#arrow" + (index + 1) + "_next")
        });
    });
    // 랭크 ajax 부분
    $.ajax({
        type: "post",
        url: "/rankAjax",
        data: {
            cateNum: 20
        },
        dataType: "json",
        success: function (response) {
            console.log(response);
            $(".rank-category-title:eq(0)").text("IT·프로그래밍");
            $(".sumPrice:eq(0)").empty
            console.log(response["sump"]);
            $(".sumPrice:eq(0)").text(response[0].sumPrice + "원");
            $(".free-id:eq(0)").append("<a href='/introduceFrm?mId=" + response[0].mId +
                "&reqPage=1'>" +
                response[0].mId + "</a>");
            for (let i = 1; i < response.length; i++) {
                $(".rank-list:eq(0)").find(".rank-col-2:eq(" + (i-1) + ")").text(response[i]
                    .sumPrice + "원");
                $(".rank-list:eq(0) .rank-col-3:eq(" + (i-1) + ")").append(
                    "<a href='/introduceFrm?mId=" + response[i].mId +
                    "&reqPage=1'>" +
                    response[i].mId + "</a>");
            }
        }
    });
    $.ajax({
        type: "post",
        url: "/rankAjax",
        data: {
            cateNum: 40
        },
        dataType: "json",
        success: function (response) {
            console.log(response);
            $(".rank-category-title:eq(1)").text("교육");
            $(".sumPrice:eq(1)").empty
            console.log(response["sump"]);
            $(".sumPrice:eq(1)").text(response[0].sumPrice + "원");
            $(".free-id:eq(1)").append("<a href='/introduceFrm?mId=" + response[0].mId +
                "&reqPage=1'>" +
                response[0].mId + "</a>");
            for (let i = 1; i < response.length; i++) {
                $(".rank-list:eq(1)").find(".rank-col-2:eq(" + (i-1) + ")").text(response[i]
                    .sumPrice + "원");
                $(".rank-list:eq(1) .rank-col-3:eq(" + (i-1) + ")").append(
                    "<a href='/introduceFrm?mId=" + response[i].mId +
                    "&reqPage=1'>" +
                    response[i].mId + "</a>");
            }
        }
    });
    $.ajax({
        type: "post",
        url: "/rankAjax",
        data: {
            cateNum: 70
        },
        dataType: "json",
        success: function (response) {
            console.log(response);
            $(".rank-category-title:eq(2)").text("주문제작");
            $(".sumPrice:eq(2)").empty
            console.log(response["sump"]);
            $(".sumPrice:eq(2)").text(response[0].sumPrice + "원");
            $(".free-id:eq(2)").append("<a href='/introduceFrm?mId=" + response[0].mId +
                "&reqPage=1'>" +
                response[0].mId + "</a>");
            for (let i = 1; i < response.length; i++) {
                $(".rank-list:eq(2)").find(".rank-col-2:eq(" + (i-1) + ")").text(response[i]
                    .sumPrice + "원");
                $(".rank-list:eq(2) .rank-col-3:eq(" + (i-1) + ")").append(
                    "<a href='/introduceFrm?mId=" + response[i].mId +
                    "&reqPage=1'>" +
                    response[i].mId + "</a>");
            }
        }
    });
    $(".category-title").hover(function (e) {
        $(this).parent().children("a").eq(1).children("div").addClass("cate-hover");
    }, function (e) {
        $(this).parent().children("a").eq(1).children("div").removeClass("cate-hover");
    })
});