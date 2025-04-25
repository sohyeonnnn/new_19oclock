
	$(function() {
		var mId = $('#haha').val();
		$(window).scroll(function() {
			if ($(this).scrollTop() > 200) {
				$('#topBtn').fadeIn();
			} else {
				$('#topBtn').fadeOut();
			}
		});
		$('#topBtn').click(function() {
			$('html, body').animate({
				scrollTop : 0
			}, 300);
			return false;
		});

		//새 jsp(reviewListSize.jsp)파서 값 불러내고 다시 불러오는 방식 ajax 평가 개수구하기
		$.ajax({
			type : "get",
			url : "/reviewListSize.do",
			data : {
				mId : mId
			},
			success : function(result) {
				console.log(result);
				if(result=='[null]'){
					result=[0];
				}
				$('#listSize').html(result + "개의 평가").css("color", "gray");
				$('#listSize2').html(result + "개의 평가").css("color", "gray")
						.css("margin-left", "0px");
			}
		});
		//평점 평균 구하기 ajax
		/* $.ajax({
			type : "get",
			url : "/sRateAVG.do",
			data : {
				mId : mId
			},
			success : function(result) {
				console.log(result);
				if (result = 'null') {
					$('#sRate').html("평점0.0").css("color", "gray");
					$('#sRate2').html("평점0.0").css("color", "gray");
				} else {
					$('#sRate').html("평점" + result).css("color", "gray");
					$('#sRate2').html("평점" + result).css("color", "gray");
				}

			}
		}); */

	});
