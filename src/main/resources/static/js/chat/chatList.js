
		$(".goRequest")
				.click(
						function() {
							opener.parent.location.href = "requestList.do?reqPage=1&order=new&subject=all&keyword=";
							window.close();
						});

		$(".goMain").click(function() {
			opener.parent.location.href = "/";
			window.close();
		});

		$(".list").click(function() {
			var cNo = $(this).find(".cNo").val();
			var sNo = $(this).find(".sNo").val();
			cNo = Number(cNo);
			sNo = Number(sNo);
			/* 상대방아이디 */
			var freeId = $(this).find(".freeId").val();
			console.log("상대아이디:" + freeId);
			/* 탈퇴한회원일때 */
			if (freeId == '') {
				alert("탈퇴한 회원입니다");

			} else {
				 	location.href = "/enterRoom.do?cNo=" + cNo + "&sNo="
							+ sNo + "&yourId=" + freeId; 
			}
		});
