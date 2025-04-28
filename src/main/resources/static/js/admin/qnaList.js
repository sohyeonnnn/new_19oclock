
        $(document).ready(function () {

            // 체크박스 토글기능
            function toggleCheckBox($target) {
                if ($target.prop("checked") == true)
                    $target.prop("checked", false);
                else
                    $target.prop("checked", true);
            }
            $(".tr").on("click", function (e) {
                console.log("Asd");
                toggleCheckBox($(this).children("td:eq(0)").children("input:checkbox"));
            });
            $("input:checkbox").on("click", function (e) {
                e.stopPropagation();
            });

            //모달창 관련
            $(".background-screen").on("click", function (e) {
                $(this).hide();
                $(".qna-view-container").hide();
                $(".qna-view-container-answer").hide();
            });
            $(".modal-exit").on("click", function (e) {
                $(".qna-view-container").hide();
                $(".qna-view-container-answer").hide();
                $(".background-screen").hide();
            });
            $(".list-title").on("click", function (e) {
                //ajax
                $.ajax({
                    type: "post",
                    url: "/questionViewAjax.do",
                    data: {
                        qNo: $(this).siblings("input:hidden").val()
                    },
                    dataType: "json",
                    success: function (response) {
                        $(".qna-view-title").empty();
                        $(".qna-view-title").append("제목: " + response.qTitle);
                        $(".qna-view-file").empty();
                        $(".qna-view-file").show();
                        $(".qna-view-file").append("파일: " + response.filePath);
                        $(".qna-view-content:eq(0)").empty();
                        $(".qna-view-content:eq(0)").append(response.qContent);
                    }
                });
                $(".background-screen").show();
                $(".qna-view-container").show();
                e.stopPropagation();
            });
            $(".answer-list-title").on("click", function (e) {
                //ajax
                $.ajax({
                    type: "post",
                    url: "/questionViewAjax.do",
                    data: {
                        qNo: $(this).siblings("input:hidden").val()
                    },
                    dataType: "json",
                    success: function (response) {
                        $(".qna-view-title").empty();
                        $(".qna-view-title").append("답변: " + response.qTitle);
                        $(".qna-view-file").empty();
                        $(".qna-view-file").hide();
                        $(".qna-view-content").empty();
                        $(".qna-view-content").append(response.answerContent);
                    }
                });
                $(".background-screen").show();
                $(".qna-view-container").show();
                e.stopPropagation();
            });
            // 답변창
            $(".btn-answer").on("click", function (e) {
                $("#answer_No").val($(this).siblings("input:hidden").val());
                $(".background-screen").show();
                $(".qna-view-container-answer").show();
                e.stopPropagation();
            });
            // 수정창
            $(".btn-update").on("click", function (e) {
                $("#answer_No").val($(this).siblings("input:hidden").val());
                $(".background-screen").show();
                $(".qna-view-container-answer").show();
                e.stopPropagation();
            });
            // 
            $("#loc").val(window.location.href);

            // 셀렉트 박스 부분
            $("select").on("change", function () {
                location.href = "/manageQnA.do?list_num="+$("select>option:selected").val();
            });
        });
