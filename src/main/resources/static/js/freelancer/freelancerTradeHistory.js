
	$(document).ready(function(){
		//메뉴 고정
		 $(".menu").children().eq(3).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
		 $(".menu").children().eq(3).find('img').css({'display':'inline'});
		 
		 var containerHeight = $(".board-wrap").height();
		 $(".page-wrap").height(containerHeight+200);
	});
	
	//거래 세부 내역 확인
	function trade_open(obj){
		var tNo = $(obj).html();
		var _left = Math.ceil(( window.screen.width - 473 )/2);
		window.open('/serviceTradeView.do?tNo='+tNo, '거래 내용 확인', 'width=473, height=480, left='+_left+', top=50, scrollbars=no, location=no, resizable=no');
		return false;
	}
	
	//작업 완료 번튼 누르면
	 $('.review-btn').click(function(event){
		 var tNo = $(this).prev().val(); //tno값
		 var mNo = $(this).prev().prev().val(); //mNo값
		 var heigh = event.pageY;
		 console.log(tNo+"/"+mNo+"/"+heigh)

		 $.ajax({
			type : "post",
			url : "/updateTStatus.do",
			data : {tNo : tNo},
			error : function() {
				alert("실패");
			},
			success : function(result) {			
				$(".modal_content").append("<input type='hidden' value="+tNo+" id='modal_tNo'>");
				$(".modal_content").append("<input type='hidden' value="+mNo+" id='modal_mNo'>");
				
				var _left = Math.ceil(( window.screen.width - 400 )/2);
				var _top = event.pageY-200;
				$("#modal").css({'position':'absolute','top':_top,'left':_left}).toggle('slow');
			}
		});
	 });
	 
	//다신 만나기 싫어요 외 다른거 누르면
	 $('.modal_close').click(function(event){
		 alert('소중한 의견 감사합니다.');
		 $("#modal").toggle('slow');
		 location.reload();
	 });
	 
	//다신 만나기 싫어요 버튼 누르면
	 $('a[href="#hate"]').click(function(event){
		var tNo = $('#modal_tNo').val();
		var mNo = $("#modal_mNo").val();
		alert('소중한 의견 감사합니다.');
		location.href="/updateWarningCount.do?mNo="+mNo;
	 });
	
	//거래상세보기 창에서 서비스 상세보기를 누르면
	function gotoServiceView(sNo,mNo){
		location.href="/serviceView.do?sNo="+sNo+"&reqPage=1&mNo="+mNo;
	}
		
