# URL Shortener Application

## 요구사항
- Springboot 기반 Application으로 개발되어야 합니다.
- URL 입력폼과 변환 결과로 Shortening된 URL을 확인할 수 있어야 합니다.
- URL Shortening Key는 8 Character 이내로 생성되어야 합니다.
- 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
- 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
- Shortening된 URL을 요청 받으면 원래 URL로 리다이렉트 합니다.
- Database 사용은 필수가 아닙니다.

## 개발 프레임워크 
* Java11
* Spring boot
* Spring data jpa
* H2
* lombok
* apache commons-lang3
* maven

## 빌드 및 실행 방법
  - java11 이상 필요

  * 소스를 내려 받는다.
  ```bash
  $ git clone https://github.com/Hyunzzang/shortener.git
  ```

  * 프로젝트 디렉토리의 mvnw으로 빌드 (mac os)
  ```bash
  $ cd shortener
  $ ./mvnw clean package
  ```
  * mvnw으로 빌드 (윈도우)
  ```bash
  ./mvnw.cmd clean install
  ```

  * 빌드 후 java -jar 으로 실행 방법 
  ```bash
  $ java -jar target/shortener-0.0.1-SNAPSHOT.jar
  ```
  * mvnw으로 spring boot 실행 방법
  ```bash
  $ ./mvnw spring-boot:run
  ```


### api 설명
#### 1. URL 변환
  * 정상적인 URL를 짧은 URL로 변경 하는 api
  
  > [POST] /api/v1/url/shorten
  
  > [Header] content-type: application/json
  ```
  [Request Body]
  { 
    "originalUrl": "원문 URL"
  }
  ```
  ```
  [Response Body]
  { 
    "shortUrl": "짭게 변경된 URL"
  }
  ```
 

### 짧게 변경된 URL로 브라워저에서 접속하기 
  > [GET] /redirect?shortUrl={짧게 변경된 url}


### 테스트

#### URL 변환 요청
```
curl -X POST http://localhost:8080/api/v1/url/shorten -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{ "originalUrl":"https://en.wikipedia.org/wiki/URL_shortening"}'
```

#### 변환 URL 접속하기
  * 웹브라우저에서 리다이렉트로 접근
  
```
웹브라워저 주소창에 입력

http://localhost:8080/redirect?shortUrl=http://localhost/b
```