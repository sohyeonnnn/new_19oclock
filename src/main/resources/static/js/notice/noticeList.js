<script>
    $(function () {
    $(".btn-search").click(function() {
        var keyword = $(".keyword").val();
        location.href = "/noticeList.do?reqPage=1&keyword="+keyword;
    })
})

</script>
