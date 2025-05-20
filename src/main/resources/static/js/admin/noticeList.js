$(document).ready(function () {
	$(function () {
		$(".search-btn").click(function() {
			var keyword = $("#keyword").val();
			location.href = "/adminNoticeList?reqPage=1&keyword="+keyword;
		})

		$(".button").click(function () {
			location.href="/noticeWriteFrm";
		})
	})

});
