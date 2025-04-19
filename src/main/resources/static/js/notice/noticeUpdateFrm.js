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

        var result = confirm("수정 하시겠습니까?");
        if(result){
            $("#reqForm").submit();
        }
        else{
            return;
        }
    });
})