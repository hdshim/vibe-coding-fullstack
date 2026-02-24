# 프로젝트 명세서

최소 기능 스프링 부트 애플리케이션(`vibeapp`)의 기술 명세서입니다.

## 1. 프로젝트 개요
- **프로젝트 명**: vibeapp
- **설명**: 프리미엄 UI를 갖춘 기능형 구조의 스프링 부트 웹 애플리케이션
- **그룹**: `com.example`
- **아티팩트**: `vibeapp`
- **메인 클래스명**: `VibeApp`

## 2. 개발 환경 설정
- **JDK**: JDK 25 이상
- **언어**: Java
- **빌드 도구**: Gradle 9.3.0 이상 (Groovy DSL 사용)
- **프레임워크**: Spring Boot 4.0.1 이상

## 3. 기술 스택 및 라이브러리
- **템플릿 엔진**: Thymeleaf
- **스타일링**: Vanilla CSS (현대적인 다크 모드 및 글래스모피즘 디자인)
- **패키지 구조**: 기능 중심(Functional) 구조
  - `com.example.vibeapp.home`: 홈 페이지 기능
  - `com.example.vibeapp.post`: 게시글 CRUD 기능
- **데이터 저장**: In-memory (CopyOnWriteArrayList 기반 Thread-safe 저장소)

## 4. 구성 설정 (Configuration)
- **설정 파일 형식**: YAML (`application.yml`)
- **주요 기능**:
  - 게시글 목록 페이징 처리 (페이지당 5개)
  - 게시글 생성, 조회(상세), 수정, 삭제(CRUD)
  - 반응형 프리미엄 웹 디자인

## 5. 프로젝트 메타데이터 요약
| 항목 | 사양 |
| :--- | :--- |
| 그룹 (Group) | com.example |
| 아티팩트 (Artifact) | vibeapp |
| 설명 (Description) | 기능형 구조와 프리미엄 UI를 갖춘 게시판 애플리케이션 |
| 메인 클래스 (Main Class) | VibeApp |
| 설정 형식 | YAML |
| JDK 버전 | 25 이상 |
| Spring Boot 버전 | 4.0.1 이상 |
| 빌드 도구 | Gradle 9.3.0+ |
| 템플릿 엔진 | Thymeleaf |
| 디자인 컨셉 | 프리미엄 다크 모드, 글래스모피즘 |
