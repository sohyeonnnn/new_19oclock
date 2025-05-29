$(function() {
	$(".messages").scrollTop($(".messages")[0].scrollHeight);

});

function deleteMsg(ccNo) {
	console.log("delete!");
	$.ajax({
		url : "/deleteMsg.do",
		type : "post",
		async : false,
		data : {
			ccNo : ccNo
		},
		success : function(data) {
			location.reload();
		}
	});
}

function sendMsg(myId, cNo) {

	/* 삭제된 서비스면 alert */
	var deleted = "${service.deleteStatus }";
	if (deleted == 'y'.charAt(0)) {
		alert("메세지를 보낼수 없습니다!");
	} else {

		// 현재 시간 구하기
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		if (month < 10) {
			month = "0" + month;
		}
		var day = now.getDate();
		if (day < 10) {
			day = "0" + day;
		}
		var hour = now.getHours();
		var minute = now.getMinutes();
		if (minute < 10) {
			minute = "0" + minute;
		}
		var ampm;
		if (hour < 12) {
			ampm = "오전 ";
			if (hour < 10) {
				hour = "0" + hour;
			}
		} else {
			ampm = "오후 ";
			if (hour > 12) {
				hour = hour - 12;
				if (hour < 10) {
					hour = "0" + hour;
				}
			}
		}

		var cNo = ${cNo}; //방번호
		cNo = Number(cNo);
		var date = year + "년 " + month + "월 " + day + "일";
		var time = ampm + hour + ":" + minute; //보낸 시간
		var content = $(".message").val(); //메세지 내용

		console.log("방번호: " + cNo);
		console.log("보내는사람: " + myId);
		console.log("날짜: " + date);
		console.log("시간: " + time);

		if (content != "") {
			$.ajax({
				url : "/insertChat.do",
				type : "post",
				async : false,
				data : {
					cNo : cNo,
					myId : myId,
					date : date,
					time : time,
					content : content
				},
				success : function(data) {
					$(".messages").append(
						"<p class='sent'>" + content
						+ "<br><span class='chat-time'>" + ampm
						+ hour + ":" + minute + "</span></p>");

					$(".message").val('');
					/* $(".messages").scrollTop($(".messages")[0].scrollHeight); */
					location.reload();
				}
			});
		}
	}
}

// 엔터 누르면 전송
$(".message").keyup(function(e) {
	var cNo = ${cNo}; //방번호
	cNo = Number(cNo);
	if (e.keyCode == 13)
		sendMsg('${sessionScope.loginMember.MId}', cNo);
});

function deleteChat() {
	var cNo = ${cNo}; //방번호
	cNo = Number(cNo);
	var mGrade = ${loginMember.MGrade};
	var mId = "${loginMember.MId}";
	check = confirm("대화내용이 모두 삭제됩니다");
	if (check) {

		$.ajax({
			url : "/deleteChat.do",
			type : "post",
			async : false,
			data : {cNo : cNo},
			success : function(data) {
				console.log("삭제성공");
				location.href = "/chatList.do?mGrade=" + mGrade
					+ "&mId=" + mId
			}
		});
	}
}

$(".service-title").click(
	function() {
		/* 삭제된 서비스면 alert */
		var deleted = "${service.deleteStatus }";
		if (deleted == 'y'.charAt(0)) {
			alert("삭제된 서비스입니다!");
		} else {
			console.log("click!");
			var sNo = ${service.SNo};
			sNo = Number(sNo);
			opener.parent.location.href = "/serviceView.do?sNo="
				+ sNo + "&reqPage=1&mNo=" + ${loginMember.MNo};
		}
	});

$(".qna").click(function() {
	console.log("click!!");
	opener.parent.location.href = "/qna.do?page=1";
});