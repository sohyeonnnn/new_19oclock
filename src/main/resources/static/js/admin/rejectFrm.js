
		$("#submit").click(
				function() {

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
					var mNo = "${mNo}";
					mNo = Number(mNo);
					var userId = "${service.MId }";
					var freeId = "admin";
					var sTitle="${service.STitle }";
					//선택한 이유
					var content = $("input[type=radio]:checked").next().html(); 
					content="서비스 <b> ["+sTitle+"]</b>의 등록이 <b>거절</b>되었습니다.<br>(이유 : "+content+")";
					
					//admin과 회원간의 채팅방 생성
					//거절이유보내기
					//adminApproval='n'
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
							//거절이유 보내기
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
									//adminApproval='n'
									console.log("22222222");
									var sNo = "${service.SNo}";
									sNo = Number(sNo);
									console.log(sNo);
									$.ajax({
										url : "/rejectService.do",
										type : "post",
										async : false,
										data : {
											sNo:sNo
										},
										success : function(data) {
											console.log("3333333");
											opener.parent.location.reload();
											window.close();    
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
