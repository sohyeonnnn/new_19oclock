
		$(document).ready(function(){
			//메뉴 고정
			 $(".menu").children().eq(2).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
			 $(".menu").children().eq(2).find('img').css({'display':'inline'});
			 
			var order = "${order}";
			
			if(order == "approved"){
				$(".array").val("approved").prop("select","seleted");
			}else if ( order == "rejected")
				$(".array").val("rejected").prop("select","seleted");
			
			$(".array").change(function () {
				var order = $(".array").val();
				location.href="/freelancerServiceList?order="+order;
			})

			function del(){
				var sNo = $("#sNo").val();
				var confirm_test = confirm("해당 서비스를 삭제할까요?");

				if(confirm_test == true){
					location.href = "/delService?sNo="+sNo;
				}

			};

		});

		

