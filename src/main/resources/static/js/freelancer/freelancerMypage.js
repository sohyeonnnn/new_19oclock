
	document.addEventListener('DOMContentLoaded', function() {
		//메뉴 고정
		 $(".menu").children().eq(0).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
		 $(".menu").children().eq(0).find('img').css({'display':'inline'});
		 
		 //높이 조절
		 var containerHeight = $(".board-box").height();
		 $(".page-wrap").height(containerHeight+300);

		//소개글로 이동
		$(".introduce-btn").click(function(){
			var memberNo = $("#mNo").val();
			location.href = "/introduceFrm?memberNo="+memberNo+"&reqPage=1";
		});

	});
	
