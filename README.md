# 인스타그램 클론
>인스타그램 서비스  

[미술관 서비스](https://bit.ly/3dRzExf) <br>
https://www.notion.so/9ed1f0f524614e42a810af741e6fd59f

</br>

## 1. 제작 기간 & 참여 인원
- 2021년 6월 ~ 2021년 6월
- 개인 프로젝트


</br>

## 2. 의존성
#### `Back-end`
  - Java 11
  - Spring Boot 2.4.3
  - Gradle
  - Spring Data JPA
  - Spring Security
  - MariaDB
  - Spring Security
  
#### `Front-end`
  - HTML/CSS
  - JavaScript

</br>

## 3. ERD 설계
(http://naver.me/FwnGhGE3)

</br>

## 4. yml 설정
```
server:
  port: 8080
  servlet:
    encoding:
      charset: utf-8
      enabled: true

# view Resolver 설정
spring:
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/photogram?serverTimezone=Asia/Seoul
    username: 
    password: 

  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        use_sql_comments: true



  servlet:
    multipart:
      enabled: true
      max-file-size: 2MB

  security:
    user:
      name: test
      password: 1234

    oauth2:
      client:
        registration:
          facebook:
            client-id: 
            client-secret: 
            scope:
            - public_profile
            - email


file:
  path: C:/workspace/springbootwork/upload/

```

## 5. 기능
  - Spring Data JPA와 REST API 형식의 CRUD 구현
  - Ajax 비동기 처리방식 좋아요, 댓글 기능 구현
  - 무한 스크롤 페이징처리
  - OAuth2 인증 프로토콜을 통한 소셜네트워크 로그인처리

  
</br>

## 6. 회고 / 느낀점
> 메인 기능 구현과 상호 연관되어있는 공통기능(전,후처리)를 프록시 패턴, AOP를 이용하여 중복없이 효율적으로 코드를 짤수 있었으며, 
  ExceptionHandler를 통해, 전역적으로 발생하는 예외에 대하여 통합적으로 관리할 수 있었다.
  
  
