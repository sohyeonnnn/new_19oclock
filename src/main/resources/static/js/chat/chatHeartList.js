
		function deleteFavorite(sNo,mNo) {
			$.ajax({
				url : "/deleteHeart.do",
				type : "get",
				data : {
					sNo : sNo,
					mNo : mNo
				},
				success : function(data) {
					location.reload();
				},
				error : function() {
					console.log("오류");
				}
			});
		}

		$(".goMain").click(function() {
			opener.parent.location.href = "/";
			window.close();
		});

		function startChat(sNo, userId, freeId, mNo, mGrade) {
			$.ajax({
				url : "/makeRoom.do",
				type : "post",
				async : false,
				data : {
					sNo : sNo,
					userId : userId,
					freeId : freeId,
					mNo : mNo,
					mGrade : mGrade
				},
				success : function(data) {
					location.href = "/enterRoom.do?cNo=-1&sNo=" + sNo
							+ "&myId=" + userId + "&yourId=" + freeId
							+ "&mGrade=" + mGrade
				},
				error : function() {

				}
			});
		}
