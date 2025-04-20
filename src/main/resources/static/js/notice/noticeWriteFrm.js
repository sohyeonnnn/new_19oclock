$(document).ready(function () {
    // SmartEditor2 초기화
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "smartEditor",
        sSkinURI: "/SE2/SmartEditor2Skin.html",
        fCreator: "createSEditor2",
        htParams: {
            bUseToolbar: true,
            bUseVerticalResizer: false,
            bUseModeChanger: false
        }
    });

    // 작성완료 버튼 클릭 시
    $("#savebutton").click(function (e) {
        console.log("@@@@@@@@@@@@");
      /*  e.preventDefault();

        // 에디터 내용 textarea에 반영
        oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);

        const title = $("#title").val().trim();
        const content = $("#smartEditor").val().trim();

        if (title === "") {
            alert("제목을 입력해주세요.");
            $("#title").focus();
            return;
        }

        if (
            content === "" ||
            content === "&nbsp;" ||
            content === "<br>" ||
            content === "<p>&nbsp;</p>" ||
            content === "<p><br></p>"
        ) {
            alert("본문을 입력해주세요.");
            oEditors.getById["smartEditor"].exec("FOCUS");
            return;
        }

        if (confirm("공지사항을 등록하시겠습니까?")) {
            $("#reqForm").submit();
        }*/
    });
});

/*     $(".save").click(function () {
        oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FILED",[]);

        if(getByteB(content)>4000){
            alert("입력할 수 있는 글자수를 초과되었습니다.\n현재 본문크기 : "+getByteB(content)+"byte, 제한크기 : 4000byte");
            return;
        }
    })

    function getByteB(str) {
    var Abyte = 0;
    for (var i=0; i<str.length; ++i) {
        // 기본 한글 3바이트 처리
        (str.charCodeAt(i) > 127) ? Abyte += 3 : Abyte++ ;
    }
        return Abyte;
    } */
