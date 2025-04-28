
	$(function(){
		todayIs();
	});
	function todayIs() {
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
		date = year + '년 ' + month + '월 ' + day + '일 ';
		document.getElementById("date").innerHTML = date;
	}

		
		$("#send").click(function() {
			//견적서 작성되었다고 알림, trade테이블에 insert, 작업수 1 늘려주기
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
			
			//기간,금액이 모두 작성되었는지 확인
			var d1=$("#d1").val();
			var d2=$("#d2").val();
			var price=$("#price").val();
			
			if(d1=='' || d2==''||price==''){
				alert("기간과 금액 모두 작성해주세요");
				return;
			}
			
			//시작일 종료일 비교	
			var n1=d1.split("-");
			n1=n1[0]+n1[1]+n1[2];
			n1=Number(n1);
			
			n2=d2.split("-");
			n2=n2[0]+n2[1]+n2[2];
			n2=Number(n2);
			
			if(n1>n2){
				alert("종료일은 시작일 이후로 설정해주세요.");
				return;
			}
			
			//시작일과 오늘 날짜 비교
			var today=year+month+day;
			if(n1<today){
				alert("시작일은 오늘 이후로 설정해주세요.");
				return;
			}

			var date = year + "년 " + month + "월 " + day + "일";
			var time = ampm + hour + ":" + minute; //보낸 시간
			var mNo = "${mNo}";
			mNo = Number(mNo);
			var userId = "${userId }";
			var freeId = "admin";
			var sTitle="${sTitle }";
			var freelancer="${freeId }";
			//선택한 이유
			var content ="서비스 <b> ["+sTitle+"("+freelancer+")]</b>의 견적서가 작성되었습니다.<br>마이페이지에서 확인 후 <b>결제</b>해주세요.";
			
			//admin과 회원간의 채팅방 생성
			//메세지보내기
			//trade테이블 insert
			$.ajax({
				url : "/makeRoom.do",
				type : "post",
				async : false,
				data : {
					sNo : 0,
					userId : userId,
					freeId : freeId,
					mNo : mNo
				},
				success : function(data) {
					console.log("111111");
					//방만들기 성공했을때
					var cNo = data.cNo; //방번호 
					cNo = Number(cNo);
					console.log(freeId);
					console.log(date);
					console.log(time);
					console.log(content);
					//알림
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
							//trade테이블 insert
							console.log("22222222");
							var sNo = "${sNo}";
							sNo = Number(sNo);
										
						 	$.ajax({
								url : "/startTrade.do",
								type : "post",
								async : false,
								data : {
									sNo:sNo,
									mNo:mNo,
									price:price,
									start:d1,
									end:d2
								},
								success : function(data) {
									
									opener.parent.location.reload();
									window.close();   
								/* 	//작업수 1 증가
									$.ajax({
										url : "/updateCount.do",
										type : "post",
										async : false,
										data : {
											sNo:sNo,
										},
										success : function(data) {
											console.log("working_count update");
											opener.parent.location.reload();
											window.close();    
										},
										error : function() {
											console.log("update실패");
										}
									});   */
	   
								},
								error : function() {

								}
							});  
						},
						error : function() {

						}
					}); 
				},
				error : function() {

				}
			}); 
			

		});