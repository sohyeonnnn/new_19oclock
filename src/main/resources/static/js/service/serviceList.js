let mainCategoryCd = $('#mainCategoryCd').val();
let categoryCd = $('#categoryCd').val();

function loadServices(page = 1, mainCategoryCd, categoryCd) {

    const order = $('#sort').val();
    const keyword = $('#keyword').val();

    $.ajax({
        url: '/serviceList',
        type: 'GET',
        data: {
            reqPage: page,
            mainCategoryCd: mainCategoryCd,
            categoryCd: categoryCd,
            order: order,
            keyword: keyword
        },
        success: function(data) {
            if (!Array.isArray(data.serviceList) || data.serviceList.length === 0) {
                const noServiceTemplate = document.querySelector('#no-service-template').textContent;
                const rendered = Mustache.render(noServiceTemplate, {});
                $('#service-container').html(rendered);
            } else {
                $.get('/template/service/serviceListTemplate.mustache', function(template) {
                    const rendered = Mustache.render(template, { serviceList: data.serviceList });
                    $('#service-container').html(rendered);
                });
            }

            $('#pagination').html(data.pageNavi);
        },
        error: function () {
            alert("서비스를 불러오는데 실패했습니다.");
        }
    });
}

$(document).ready(function() {

    loadServices(1, mainCategoryCd, categoryCd);

    const order = $('#sort').val();

    if(order == "new"){
        $('#sort').val("new").prop("selected",true);
    }else if (order == "popular"){
        $('#sort').val("popular").prop("selected",true);
    }else if (order == "review"){
        $('#sort').val("review").prop("selected",true);
    }else if (order == "lowPrice"){
        $('#sort').val("lowPrice").prop("selected",true);
    }

    $('#sort').change(function () {
        loadServices(1, mainCategoryCd, categoryCd);
    });

    $('.search-btn').click(function () {
        loadServices(1, mainCategoryCd, categoryCd);
    });

    $(document).on('click', '.category-link', function (e) {
        e.preventDefault();

        $('.category-link').removeClass('selected-category');
        $(this).addClass('selected-category');

        mainCategoryCd = $(this).data('main');
        categoryCd = $(this).data('category');
        loadServices(1, mainCategoryCd, categoryCd);
    });

    // 페이지 네비 클릭
    $(document).on('click', '.page-link[data-page]', function () {
        const page = $(this).data('page');
        loadServices(page, mainCategoryCd, categoryCd);
    });

});





