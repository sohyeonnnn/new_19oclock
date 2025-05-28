
$(document).ready (function () {
    var fCheck = $("#favoriteCheck").val();

    if(fCheck == true){
        $(".full-heart").addClass("fas");
    }

// 서비스 상세보기 탭 구현
    $(function() {
        var tab = $(".tab");
        var content = $(".tabcontent");

        $(".tabcontent").each(function(i, item) {
            $(this).hide();
            content.eq(0).show();
        });

        tab.eq(0).addClass("clickTab");

        $(".tab").click(function() {
            var index = tab.index(this);

            $(".tabcontent").each(function(idx, item) {
                if (idx == index) {
                    $(item).show();
                } else {
                    $(item).hide();
                }
            });
            $(this).siblings().removeClass("clickTab");
            $(this).addClass("clickTab");

        })

        $(".heart").click(function() {
            $(this)
        })
    });


})

// 좋아요 클릭시 찜하기 insert
function like_func() {
    var m_no = $("#user_no").val();
    var s_no = $("#s_no").val();
    var fCheck = $("#favoriteCheck").val();
    console.log("m_no:"+m_no+"s_no:"+s_no);

    if(m_no != null){
        if(fCheck == true){
            var fCon = confirm("해당 서비스를 찜한 목록에서 제거 할까요?")
            if(fCon == true){
                $.ajax({
                    url:"/deleteHeart",
                    type: "post",
                    data:{memberNo:m_no,
                        serviceNo:s_no},
                    error : function () {
                        alert("제거못함");
                    },
                    success:function(){
                        $(".full-heart").removeClass("fas");
                        location.reload();
                    }

                })
            }
        }else{
            $.ajax({
                url:"/insertHeart",
                type:"post",
                data:{memberNo:m_no,
                    serviceNo:s_no},
                success:function(){
                    $(".full-heart").addClass("fas");
                    location.reload();
                }
            })
        }

    }else{
        alert("찜하기는 로그인이 필요합니다.");
    }
}

//본인 서비스는 찜할 수 없음
function like_func_error(){
    alert("본인 서비스는 찜할 수 없습니다.");
}


function login_need() {
    alert("로그인이 필요합니다.");
}

function startChat(serviceNo, userId, freeId, memberNo, memberRole) {
    if(userId == freeId){
        alert("본인의 서비스는 문의할 수 없습니다.");
    }else{
        $.ajax({
            url : "/makeRoom",
            type : "post",
            async : false,
            data : {
                serviceNo : serviceNo,
                userId : userId,
                freeId : freeId,
                memberNo : memberNo,
                memberRole : memberRole
            },
            success : function(data) {
                /* location.href = "/enterRoom.do?cNo=-1&serviceNo=" + serviceNo
                        + "&myId=" + userId + "&yourId=" + freeId
                        + "&memberRole=" + memberRole */
                var loc="/enterRoom?chatNo=-1&serviceNo=" + serviceNo + "&myId="+ userId + "&yourId=" + freeId+"&memberRole="+memberRole
                var _left = Math.ceil(( window.screen.width - 530 )/2);
                window.open(loc, '', 'width=530, height=630, left='+_left+', top=50, location=no,scrollbars=no,location=no, resizable=no');
            },
            error : function() {

            }
        });
    }
}

/*
       $(function () {
           var containerHeight = $(".serviceContent").height();
           $(".contentWrap").height(containerHeight+400);
       }) */


