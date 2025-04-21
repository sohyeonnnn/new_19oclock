let oEditors = [];

$(document).ready(function () {
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "smartEditor",
        sSkinURI: "/SE2/SmartEditor2Skin.html",
        fCreator: "createSEditor2",
        htParams : {
            bUseToolbar : true,
            bUseVerticalResizer : false,
            bUseModeChanger : false
        },
        fOnAppLoad: function () {
            $('#savebutton').click(function (e) {
                e.preventDefault();

                oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
                const title = $('#title').val().trim();
                const content = $('#smartEditor').val().trim();

                if (title === "") {
                    alert("제목을 입력해주세요.");
                    $('#title').focus();
                    return;
                }

                if (content === "") {
                    alert("내용을 입력해주세요.");
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
        }

    });

    $('#Listbtn').click(function (e) {
        e.preventDefault();
        history.go(-1);
    });

});