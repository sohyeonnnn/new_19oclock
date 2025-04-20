
// 페이지 번호와 검색어를 동적으로 받을 수 있도록 설정
let currentPage = 1;

// API에서 데이터를 받아오는 함수
function loadNotices(page, keyword) {
    currentPage = page;

    fetch(`/noticeList?reqPage=${currentPage}&keyword=${keyword}`)
        .then(response => response.json())
        .then(data => {
            if(data.noticeList.length === 0){
                document.querySelector("#noticeTableBody").innerHTML =
                    `<tr><td colspan="4" style="text-align:center;">게시글이 없습니다.</td></tr>`;
            }else{
                // 받은 데이터로 테이블 생성
                generateTable(data.noticeList);
            }
            // 페이지 네비게이션 생성
            generatePageNavi(data.pageNavi);
        })
        .catch(error => console.error('Error fetching data:', error));
}

// 테이블 생성 함수
function generateTable(noticeList) {
    const tableBody = document.querySelector('#noticeTableBody');
    tableBody.innerHTML = ''; // 기존 내용 초기화

    if (noticeList.length === 0) {
        // 데이터가 없을 경우, 한 줄 표시
        const row = document.createElement('tr');
        row.innerHTML = `<td colspan="4" style="text-align: center;">등록된 공지사항이 없습니다.</td>`;
        tableBody.appendChild(row);
        return;
    }

    noticeList.forEach(notice => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${notice.nno}</td>
            <td><a href="/noticeView?nNo=${notice.nno}" class="table-title" style="display: block; height: 100%; width: 100%;">${notice.ntitle}</a></td>
            <td>일.구.시</td>
            <td>${notice.createdDate}</td>
        `;
        tableBody.appendChild(row);
    });
}

// 페이지 네비게이션 생성 함수
function generatePageNavi(pageNavi) {
    const pageNaviContainer = document.querySelector('.pageNavi');
    pageNaviContainer.innerHTML = ''; // 기존 내용 초기화

    pageNaviContainer.innerHTML = pageNavi; // 서버에서 받아온 페이지 네비게이션 삽입
}

// 처음 페이지 로드 시, 기본적으로 첫 페이지 데이터를 로드
loadNotices(currentPage, "");


$(function () {
    $(".btn-search").click(function() {
        const keyword = $(".keyword").val();
        loadNotices(1, keyword); // 검색어 반영된 첫 페이지 불러오기
    });
})