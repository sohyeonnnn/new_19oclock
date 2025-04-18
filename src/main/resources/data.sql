-- 사용자 데이터 삽입
INSERT INTO MEMBER (ENROLL_DATE, M_EMAIL, M_GRADE, M_ID, M_NAME, M_PHONE, M_PW)
SELECT '20250411', 'admin@gmail.com', 'ADMIN', 'admin', '관리자', '010-2738-8240', '$2a$10$fOYgbQQS00vAIZap6GG2qOhuKFQhaUFJDbRzzwJOJzMUZYhJzHD32'
    WHERE NOT EXISTS (SELECT 1 FROM MEMBER WHERE M_ID = 'admin');

INSERT INTO MEMBER (ENROLL_DATE, M_EMAIL, M_GRADE, M_ID, M_NAME, M_PHONE, M_PW)
SELECT '20250411', 'limsohyeonn@gmail.com', 'USER', 'shlim', '임소현', '010-2738-8240', '$2a$10$fOYgbQQS00vAIZap6GG2qOhuKFQhaUFJDbRzzwJOJzMUZYhJzHD32'
    WHERE NOT EXISTS (SELECT 1 FROM MEMBER WHERE M_ID = 'shlim');

-- 카테고리 데이터 삽입
INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 1000, 1000, '디자인', NULL
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 1000);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 1000, 1100, '로고·브랜딩', 'img/index/carousel/category-11.png'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 1100);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 1000, 1200, '캘리그라피·폰트', 'img/index/carousel/category-13.png'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 1200);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 1000, 1300, '일러스트·캐리커쳐', 'img/index/carousel/category-14.png'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 1300);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 1000, 1400, '이벤트·상세 페이지', 'img/index/carousel/category-16.png'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 1400);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 1000, 1500, '간판·시공', 'img/index/carousel/category-15.png'
WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 1500);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 1000,  1600, '인쇄·홍보물·배너', 'img/index/carousel/category-12.png'
WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 1600);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 2000, 2000, 'IT·프로그래밍', NULL
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 2000);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 2000, 2100, '워드프레스', 'img/index/carousel/category-21.png'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 2100);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 2000, 2200, '웹사이트개발', 'img/index/carousel/category-22.png'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 2200);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 2000, 2300, '프로그램개발', 'img/index/carousel/category-23.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 2300);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 2000, 2400, '데이터베이스', 'img/index/carousel/category-24.png'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 2400);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 2000,2500, '게임개발', 'img/index/carousel/category-25.png'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 2500);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 2000, 2600, '파일변환', 'img/index/carousel/category-27.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 2600);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 3000, 3000, '비즈니스컨설팅', NULL
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 3000);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 3000,3100, '사업계획서·투자제안서', 'img/index/carousel/category-61.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 3100);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 3000, 3200, '인사·노무', 'img/index/carousel/category-62.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 3200);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 3000,3300, '창업컨설팅', 'img/index/carousel/category-63.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 3300);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 3000, 3400, '법률법무', 'img/index/carousel/category-64.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 3400);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 3000, 3500, '업무지원·CS', 'img/index/carousel/category-65.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 3500);

INSERT INTO CATEGORY (C_DIVISION_NO, C_NO, C_NAME, IMG_URL)
SELECT 3000, 3600, '컨설팅', 'img/index/carousel/category-60.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM CATEGORY WHERE C_NO = 3600);