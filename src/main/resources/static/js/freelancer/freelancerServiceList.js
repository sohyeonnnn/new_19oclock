
		$(document).ready(function(){
			//메뉴 고정
			 $(".menu").children().eq(2).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
			 $(".menu").children().eq(2).find('img').css({'display':'inline'});
			 
			var order = "${order}";
			var id = "${loginMember.MId}";
			console.log("order : "+order);
			console.log("memberId : "+ id);
			
			if(order == "agree"){
				$(".array").val("agree").prop("select","seleted");
			}else if ( order == "refuse")
				$(".array").val("refuse").prop("select","seleted");
			
			$(".array").change(function () {
				var order = $(".array").val();
				location.href="/freelancerServiceList.do?mId="+id+"&order="+order;
			})
			 
		});

		
 	   function del(){
    		var sNo = $("#sNo").val();
    		console.log("sNo :"+sNo);
    		var confirm_test = confirm("해당 서비스를 삭제할까요?");
    		var id = "${loginMember.MId}";
    		
    		if(confirm_test == true){
    			location.href = "/delService.do?sNo="+sNo+"&mId="+id;
    		}
    		
    	};
