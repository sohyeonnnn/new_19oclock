
        function heart_click(obj){
        	var sNo = $(obj).next().val();
            var mNo = $(obj).next().next().val();
            if($(obj).attr('value') == "fill"){
                $.ajax({
                	url : "/deleteHeart",
                	type : "get",
                	data : {"sNo":sNo, "mNo":mNo},
                	success : function(data){
                		$(obj).attr('src','/img/icon/heart_navy.png');
                        $(obj).attr('value', "empty");
                	},
                	error : function(){
                		console.log("오류");
                	}
                });
            }else{
            	$.ajax({
            		url : "/insertHeart",
            		type : "get",
            		data : {"sNo":sNo, "mNo":mNo},
            		success : function(data){
            			$(obj).attr('src','/img/icon/heart_orange.png');
                        $(obj).attr('value', "fill");
            		},
            		error : function(){
            			console.log('오류');
            		}
            	});
               
            }

        }
        
        $(document).ready(function(){
        	//메뉴 고정
	   		 $(".menu").children().eq(1).find('a').css({'margin-left':'5px', 'font-weight':'bold'});
	   		 $(".menu").children().eq(1).find('img').css({'display':'inline'});
   		 
        	$('.rate').each(function(index){
       			var rate = $('.rate').eq(index).children().eq(1).html();
       			var rateText = "";
       			for(var i =5; i>0; i--){
      	   			 if(i>rate){
      	   				 rateText = rateText + "☆";
      	   			 }else{
      	   				 rateText = rateText + "★"; 
      	   			 }
      	   		 } 
       			$('.rate').eq(index).children().eq(3).html(rateText);
       		 });
        	
        	var order = $("#order").val();
	        	if(order == "priceDown"){
	        		$("#array-select").val("priceDown").prop("selected", true);
	        	}else if(order == "priceUp"){
	        		$("#array-select").val("priceUp").prop("selected", true);
	        	}else if(order == "new"){
	        		$("#array-select").val("new").prop("selected", true);
	        	}else{
	        		$("#array-select").val("all").prop("selected", true);
	        	}
	        	
	        	//높이 동적으로 늘려줌
	        	var containerHeight = $(".container-box").height();
				$(".page-wrap").height(containerHeight+400);
				
        });
        
        $("#array-select").change(function(){
        	var order = $("#array-select").val();
        	var mNo = $("#mNo").val();
        	location.href = "/userHeartList?mNo="+mNo+"&order="+order;
        	console.log(order+"/"+mNo);
        });
