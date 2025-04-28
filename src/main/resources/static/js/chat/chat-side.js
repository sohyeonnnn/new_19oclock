
<!-- 일반고객-프리랜서 전환 -->

$(function() {
	$("#account ul").hide();
	$(".expanded").prev().append('<span class="switch"> ▼</span>');
	$(".switch").click(function(event) {
		$(".expanded").slideToggle();
		if ($(this).attr("class") == "switch") {
			$(this).addClass("active");
		} else {
			$(this).removeClass("active");
		}
		event.stopPropagation();
	});
})


	function mypage() {
		opener.parent.location.href = "/userMypage.do";
		window.close();
	}

	// 일반회원 - 프리랜서 전환
	function switchAccount() {
		var mId = "${loginMember.MId}";
		var mGrade = "${loginMember.MGrade}";
		mGrade = Number(mGrade);

		console.log(mId);
		console.log(mGrade);

		$.ajax({
					url : "/switchAccount.do",
					type : "post",
					async : false,
					data : {
						mId : mId,
						mGrade : mGrade
					},
					success : function(data) {
						// -1이 리턴되면 alert:프리랜서로 전환한적없습니다
						if (data == -1) {
							alert("프리랜서 계정이 없습니다!");
						} else if (data == 1) {
							alert("일반회원으로 전환!");
							location.href = "/chatList.do?mGrade=1&mId=${loginMember.MId}";
						} else if (data == 2) {
							alert("프리랜서로 전환!");
							location.href = "/chatList.do?mGrade=2&mId=${loginMember.MId}";
						}
					},
					error : function() {

					}
				});
	}
