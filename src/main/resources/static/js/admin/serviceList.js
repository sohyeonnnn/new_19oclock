$(document).ready(function () {
	$(function() {
		var page = "${page}";
		if (page == 'waiting') {
			$(".tab").eq(0).addClass("select");
		}
		if (page == 'approved') {
			$(".tab").eq(1).addClass("select");
		}
		if (page == 'deleted') {
			$(".tab").eq(2).addClass("select");
		}


	});

	$(".tab").eq(0).click(function() {
		locationFunc('waiting', '', '','old');
	});

	$(".tab").eq(1).click(function() {
		locationFunc('approved', '', '','new');
	});

	$(".tab").eq(2).click(function() {
		locationFunc('deleted', '', '','new');
	});

	$("input[type=radio]").change(function() {
		var status = "${page}";
		var keyword1 = "${keyword1}";
		var keyword2 = "${keyword2}";
		var order = $("input[type=radio]:checked").val()
		locationFunc(status, keyword1,keyword2, order);

	});

});

	function locationFunc(status, keyword1,keyword2,order) {
		location.href = "/manageService.do?reqPage=1&status=" + status
				+ "&keyword1=" + keyword1 + "&keyword2=" + keyword2+ "&order=" + order;
	}

	function search(page) {
		
		var keyword1 = $("option:selected").val();
		console.log(keyword1);
		var keyword2 = $(".keyword2").val();
		console.log(keyword2);
		locationFunc(page, keyword1,keyword2, ''); 
	}


	   
	  //메세지 보내기
		function sendMsg(mNo,mId,content) {
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
			if (minute < 10 & minute > 0) {
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

			
			var date = year + "년 " + month + "월 " + day + "일";
			var time = ampm + hour + ":" + minute; //보낸 시간
			var freeId = "admin";
			var mNo=Number(mNo);
			var mId=mId;

			//admin과 회원간의 채팅방 생성
			//메세지보내기
			$.ajax({
				url : "/makeRoom.do",
				type : "post",
				async : false,
				data : {
					sNo : 0,
					userId : mId,
					freeId : freeId,
					mNo : mNo
				},
				success : function(data) {
					console.log("111111");
					//방만들기 성공했을때
					var cNo = data.cNo; //방번호 
					cNo = Number(cNo);

					//메세지
					$.ajax({
						url : "/insertChat.do",
						type : "post",
						async : false,
						data : {
							cNo:cNo,
							myId:freeId,
							date:date,
							time:time,
							content:content
						},
						success : function(data) {
							console.log("메세지전송성공!");
						},
						error : function() {

						}
					}); 
				},
				error : function() {

				}
			}); 
		} 
		
	
	 
	 /* 서비스승인 */
		function acceptService(mNo,mId,sNo,sTitle){
			var mNo=Number(mNo);
			var mId=mId;
			var sNo=Number(sNo);
			var sTitle=sTitle;
			$.ajax({
				url : "/acceptService.do",
				type : "post",
				async : false,
				data : {
					mId:mId,
					sNo : sNo
				},
				success : function(data) {
					if (data == -1) {
						alert("5개 이상 등록할수 없습니다!");
					} else{
						console.log("승인 성공!");
						var content ="서비스 <b> ["+sTitle+"]</b>가 <b>등록</b>되었습니다.";
						sendMsg(mNo,mId,content);
						location.reload(); }
									
			},
			  error : function(){
                  console.log("승인 실패!");
               }
			});
		}
		
	 /* 서비스삭제 */
		function deleteService(mNo,mId,sNo,sTitle){
			console.log("삭제!!!!!");
			var mNo=Number(mNo);
			var mId=mId;
			var sNo=Number(sNo);
			var sTitle=sTitle;
			check=confirm("서비스를 삭제합니다")
			if(check){
			$.ajax({
				url : "/deleteService.do",
				type : "post",
				async : false,
				data : {
					sNo : sNo,
				},
				success : function() {
					console.log("삭제 성공!");
					var content ="서비스 <b> ["+sTitle+"]</b>가 <b>삭제</b>되었습니다.";
					sendMsg(mNo,mId,content);
					location.reload();
			},
			  error : function(){
                  console.log("삭제 실패!");
               }
			});
		}}
		
