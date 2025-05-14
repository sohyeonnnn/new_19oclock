$(document).ready(function () {
	//네이버 에디터
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors, elPlaceHolder: "smartEditor",
		sSkinURI: "SE2/SmartEditor2Skin.html",
		fCreator: "createSEditor2",
		htParams: {
			bUseToolbar: true,
			bUseVerticalResizer: false,
			bUseModeChanger: false
		}
	});

	$("#gdsImg").change(function () {
		if (this.files && this.files[0]) {
			var reader = new FileReader;
			reader.onload = function (data) {
				$(".select_img img").show();
				$(".select_img img").attr("src", data.target.result).width(120).height(120);
			}
			reader.readAsDataURL(this.files[0]);
		}
	});

	$(function () {
		$("select[name=mainCategory]").change(function () {
			var temp = $("select[name=subCategory]");
			temp.focus();
			var a = $(this).val();
			temp.children().remove();
			if (a == '0') {
				temp.append('<option value="0">서브 카테고리를 선택</option>');
			}
			if (a == '1000') {
				temp.append('<option value="1100">로고/브랜딩</option>');
				temp.append('<option value="1200">인쇄ㆍ홍보물ㆍ배너</option>');
				temp.append('<option value="1300">캘리그라피ㆍ폰트</option>');
				temp.append('<option value="1400">일러스트ㆍ캐리커쳐</option>');
				temp.append('<option value="1500">간판ㆍ시공</option>');
				temp.append('<option value="1600">이벤트 ㆍ상세페이지</option>');
				temp.append('<option value="1700">의류</option>');
				temp.append('<option value="1800">웹툰ㆍ캐릭터ㆍ이모티콘</option>');
			}
			if (a == '2000') {
				temp.append('<option value="2100">워드프레스</option>');
				temp.append('<option value="2200">웹사이트개발</option>');
				temp.append('<option value="2300">프로그램 개발</option>');
				temp.append('<option value="2400">데이터베이스</option>');
				temp.append('<option value="2500">게임개발</option>');
				temp.append('<option value="2600">보안</option>');
				temp.append('<option value="2700">파일변환</option>');
			}
			if (a == '3000') {
				temp.append('<option value="3100">영상촬영ㆍ편집</option>');
				temp.append('<option value="3200">유튜브 제작</option>');
				temp.append('<option value="3300">애니메이션제작</option>');
				temp.append('<option value="3400">더빙ㆍ녹음</option>');
				temp.append('<option value="3500">음악ㆍ사운드</option>');
			}
			if (a == '4000') {
				temp.append('<option value="4100">외국어(영어)</option>');
				temp.append('<option value="4200">외국어(기타언어)</option>');
				temp.append('<option value="4300">취미 라이프</option>');
				temp.append('<option value="4400">입시/학업</option>');
				temp.append('<option value="4500">pptㆍ프레젠테이션</option>');
				temp.append('<option value="4600">마케팅</option>');
				temp.append('<option value="4700">사진</option>');
			}
			if (a == '5000') {
				temp.append('<option value="5100">보도자료</option>');
				temp.append('<option value="5200">광고카피라이팅</option>');
				temp.append('<option value="5300">마케팅 글작성</option>');
				temp.append('<option value="5400">책ㆍ시나리오 작성</option>');
				temp.append('<option value="5500">논문</option>');
				temp.append('<option value="5600">교정ㆍ교육 첨삭</option>');
				temp.append('<option value="5700">기타</option>');
			}
			if (a == '6000') {
				temp.append('<option value="6100">사업계획서 투자제안서</option>');
				temp.append('<option value="6200">인사ㆍ노무</option>');
				temp.append('<option value="6300">창업컨설팅</option>');
				temp.append('<option value="6400">법률법무</option>');
				temp.append('<option value="6500">업무지원ㆍcs</option>');
			}
			if (a == '7000') {
				temp.append('<option value="7100">인쇄</option>');
				temp.append('<option value="7200">간판</option>');
				temp.append('<option value="7300">기념품제작</option>');
				temp.append('<option value="7400">모형제작</option>');
				temp.append('<option value="7500">시스템제작</option>');
				temp.append('<option value="7600">인테리어시공</option>');
				temp.append('<option value="7700">패키지제작</option>');
			}
		});

		//금액 입력 유효성 검사(1. 숫자만 입력/2. 5000원 이상 입력)
		$("input[name=servicePrice]").focusout(function () {
			var price = $(this).val();
			if (isNaN(price)) {
				$(this).next().next().html("※숫자만 입력해주세요.").css({'color': 'red'});
			} else if (price < 5000) {
				$(this).next().next().html("※5,000원 이상을 입력해주세요.").css({'color': 'red'});
			} else {
				$(this).next().next().html("");
			}
		});

		$("input[name=workingDate]").focusout(function () {
			var workingDate = $(this).val();
			if (isNaN(workingDate)) {
				$(this).next().next().html("※숫자만 입력해주세요.").css({'color': 'red'});
			} else {
				$(this).next().next().html("");
			}
		});

		//제출버튼 눌리면
		$("input[type=button]").click(function () {
			oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
			var mainCategory = $("#mainCategory").val();
			var price = $("input[name=servicePrice]").val();
			var day = $("input[name=workingDate]").val();
			var content = document.getElementById("smartEditor").value;
			if ($("input[name=serviceTitle]").val() == "") {
				alert('제목을 입력해주세요.');
				$("input[name=serviceTitle]").focus();
				return;
			}
			if (mainCategory == 0) {
				alert('카테고리를 선택해주세요');
				$("input[name=mainCategory]").focus();
				return;
			}
			if (isNaN(price) || price < 5000) {
				$("input[name=servicePrice]").next().next().html("※올바른 값으로 입력해주세요.").css({'color': 'red'});
				$("input[name=servicePrice]").focus();
				return;
			}
			if (isNaN(day)) {
				$("input[name=workingDate]").next().next().html("※숫자만 입력해주세요.").css({'color': 'red'});
				$("input[name=workingDate]").focus();
				return;
			}
			if ($('input[name=ssImg]').val() == "") {
				alert("이미지를 등록해주세요.");
				$('input[name=ssImg]').focus();
				return;
			}
			if (content == "" || content == null || content == '&nbsp;' || content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>') {
				alert("본문을 작성해주세요.");
				oEditors.getById["smartEditor"].exec("FOCUS");
				return;
			}
			if (getByteB(content) > 4000) {
				alert("입력할 수 있는 글자수를 초과되었습니다.\n현재 본문크기 : " + getByteB(content) + "byte, 제한크기 : 4000byte");
				return;
			}
			var result = confirm("등록 하시겠습니까?");
			if (result) {
				$("#joinForm").submit();
			} else {
				return;
			}
		});
	});
});
	function getByteB(str) {
		var Abyte = 0;
		for (var i=0; i<str.length; ++i) {
			// 기본 한글 3바이트 처리
			(str.charCodeAt(i) > 127) ? Abyte += 3 : Abyte++ ;
		}
			return Abyte;
		}
