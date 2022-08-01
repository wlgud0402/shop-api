# 상품, 카테고리, 브랜드 API 개발

## 프로젝트
    - Spring Boot를 사용하여 개발되었습니다.
    - DB - MySQL (도커사용)

## 실행방법
    1. docker-compose up
        - 도커를 사용해 mysql을 띄웁니다.
        - mysql의 경우 test/resources/shema/shop_base_ddl.sql을 통해 기본 테이블을 생성합니다.
    2. Spring Boot 서버를 실행합니다.

## DB설정
    1. read-only 어노테이션이 붙은 요청은 slave DB로 요청됩니다.
    2. 기본 @Transactional의 경우 write용 DB로 요청


<img width="884" alt="image" src="https://user-images.githubusercontent.com/61821825/182177001-1c0dfdd1-391f-4981-b8ca-968d9634ac84.png">
