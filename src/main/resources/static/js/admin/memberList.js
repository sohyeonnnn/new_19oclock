
$(document).ready(function () {
	$(function() {
		var page = "${page}";
		if (page == 'all') {
			$(".tab").eq(0).addClass("select");
		}
		if (page == 'free') {
			$(".tab").eq(1).addClass("select");
		}
		if (page == 'black') {
			$(".tab").eq(2).addClass("select");
		}

	});

	$(".tab").eq(0).click(function() {
		locationFunc('all', '', 'new');
	});

	$(".tab").eq(1).click(function() {
		locationFunc('free', '', 'new');
	});

	$(".tab").eq(2).click(function() {
		locationFunc('black', '', 'new');
	});

	$("input[type=radio]").change(function() {
		var grade = "${page}";
		var keyword = "${keyword}";
		var order = $("input[type=radio]:checked").val()
		locationFunc(grade, keyword, order);

	});


});

	function locationFunc(grade, keyword, order) {
		location.href = "/manageMember?reqPage=1&grade=" + grade
				+ "&keyword=" + keyword + "&order=" + order;
	}

	function search(page) {
		var keyword = $(".keyword").val();
		locationFunc(page, keyword, '');
	}


	function deleteMember(mId,mNo) {
		check = confirm(mId + "님을 탈퇴시킵니다")
		if (check) {
			location.href="/deleteMember?mId="+mId+"&mNo="+mNo;
		}
	}
