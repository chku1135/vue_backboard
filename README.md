# 백엔드 (Spring Boot) 프로젝트

본 프로젝트는 Vue.js 프론트엔드와 연동되는 Spring Boot 기반의 백엔드 애플리케이션입니다.

## 주요 기술 스택
- Java 17
- Spring Boot 3.2.4
- Maven
- MariaDB (Galera Cluster)

## 시작하기
1. Java 17 이상이 설치되어 있어야 합니다.
2. `src/main/resources/application.yml` 파일을 확인하여 DB 설정을 환경에 맞게 수정하세요.
3. Maven 빌드: `mvn clean install`
4. 애플리케이션 실행: `mvn spring-boot:run`

## 프로젝트 구조
- `src/main/java/com/vue_test`: 프로덕션 코드 (Controller, Service, Entity, Repository)
- `src/test/java/com/vue_test/test`: 테스트 코드
