
    $(".switch").click(function(){
        var grade = $(this).prev().val();
        var mId = $(this).prev().prev().val();
        var mPw = $(this).prev().prev().prev().val();

        //post로 넘기기
        var form = document.createElement("form");
        form.action = "/changeGrade";
        form.method = "post";

        //id 추가
        var input_mId = document.createElement("input");
        input_mId.setAttribute("type", "hidden");
        input_mId.setAttribute("name", "mId");
        input_mId.setAttribute("value", mId);
        form.appendChild(input_mId);
        //mPw 추가
        var input_mPw = document.createElement("input");
        input_mPw.setAttribute("type", "hidden");
        input_mPw.setAttribute("name", "mPw");
        input_mPw.setAttribute("value", mPw);
        form.appendChild(input_mPw);
        //grade 추가
        var input_grade = document.createElement("input");
        input_grade.setAttribute("type", "hidden");
        input_grade.setAttribute("name", "grade");
        input_grade.setAttribute("value", grade);
        form.appendChild(input_grade);

        document.body.appendChild(form);
        form.submit();
    });

    function errorMsg(){
        alert('프리랜서 프로필을 작성해주세요');
    }

    //생성된 서비스가 5개면 더이상 생성할 수 없게 함.(승인되고 삭제안된 개수)
    function isPossibleMakeService(){
        var mNo = $("#memberNo").val();
        console.log(mNo);
        $.ajax({
            type : "get",
            url : "/isPossibleMakeService",
            data : {
                mNo:$("#memberNo").val()
            },
            dataType: "json",
            success : function(response){
                if(response.data <5){
                    console.log("@@@");
                   // location.href = "/serviceJoinFrm?MNo="+mNo;
                }else{
                    alert('서비스를 생성할 수 있는 개수를 초과했습니다. \n현재 서비스 개수 : 5개');
                }
            },error : function(){
                console.log("오류");
            }
        });
    }
