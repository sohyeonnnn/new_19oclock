let oEditors = [];

$(document).ready(function () {
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "smartEditor",
        sSkinURI: "/SE2/SmartEditor2Skin.html",
        fCreator: "createSEditor2",
        fOnAppLoad: function () {
            // 작성완료 버튼 클릭 시 안전하게 접근
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
                    return;
                }
                $('#reqForm').submit();

            });
        }

    });

    $('#Listbtn').click(function (e) {
        e.preventDefault();
        history.go(-1);
    });

});