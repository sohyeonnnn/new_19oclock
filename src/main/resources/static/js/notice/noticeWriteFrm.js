var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef : oEditors, elPlaceHolder : "smartEditor",
    sSkinURI : "SE2/SmartEditor2Skin.html",
    fCreator : "createSEditor2",
    htParams : {
        bUseToolbar : true,
        bUseVerticalResizer : false,
        bUseModeChanger : false
    }
});

$(function() {
    $("#savebutton").click(function() {
        oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);

        var title = $("#title").val();
        var content = document.getElementById("smartEditor").value;
        if (title == null || title == "") {
            alert("제목을 입력해주세요.");
            $("#title").focus();
            return;
        }
        if(content == "" || content == null || content == '&nbsp;' || content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){
            alert("본문을 작성해주세요.");
            oEditors.getById["smartEditor"].exec("FOCUS");
            return;
        }

        var result = confirm("등록 하시겠습니까?");
        if(result){
            $("#reqForm").submit();
        }
        else{
            return;
        }
    });
})


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
