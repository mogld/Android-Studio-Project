# 백신 추천 애플리케이션

## 개요
백신 추천 애플리케이션은 사용자의 나이, 건강 상태 등 주요 정보를 바탕으로 맞춤형 백신을 추천해주는 안드로이드 기반 애플리케이션입니다. 또한, 다양한 질병 정보와 감염병 관련 최신 소식지를 제공하여 사용자가 건강 관리를 쉽게 할 수 있도록 돕습니다.

---

## 와이어프레임 및 화면 구성
<img src="images/whole_screen.png" alt="Main Screen" width="500">

### 1. 메인 화면
<img src="images/main_screen.png" alt="Main Screen" width="400">

### 2. 질병 카드 화면
<img src="images/disease_cards.png" alt="Disease Cards Screen" width="400">

### 3. 소식지 화면
<img src="images/newsletter_screen.png" alt="Newsletter Screen" width="400">

### 4. 추천 정보 입력 화면
<img src="images/recommendation_input.png" alt="Recommendation Input Screen" width="400">

### 5. 백신 추천 결과 화면
<img src="images/vaccine_recommendation.png" alt="Vaccine Recommendation Result Screen" width="400">

---

## 목차
1. [주요 기능](#주요-기능)
   - [핵심 개발 내용](#핵심-개발-내용)
2. [기술적 세부 사항](#기술적-세부-사항)
   - [데이터베이스 설계](#데이터베이스-설계)
   - [데이터 흐름](#데이터-흐름)
3. [팀 역할 분담](#팀-역할-분담)
4. [결론](#결론)

---

## 주요 기능

### 핵심 개발 내용
1. **백신 추천 로직**:
   - 사용자의 건강 상태와 나이를 기준으로 접종해야 할 백신 필터링.
   - **주요 구성 요소**:
     - **Disease Model**: 연령 필터 및 금기사항을 이진값(Binary)으로 저장.
     - **RecommendationService**: 사용자 입력 데이터를 바탕으로 백신 추천 결과 계산.
   - `DiseaseRepository`를 활용해 효율적인 데이터 접근 구현.

2. **질병 카드 기능**:
   - 외부 서버에서 SVG 포맷 이미지를 불러와 GlideToVectorYou 라이브러리를 이용해 화면에 표시.
   - 질병 정보를 동적으로 조회 및 표시.

3. **소식지 다운로드**:
   - 안드로이드 `DownloadManager`와 `CoroutineScope`를 활용해 비동기적으로 소식지 다운로드.
   - 다운로드 완료 후 기본 파일 리더로 열기 기능 포함.

4. **UI 네비게이션**:
   - `ViewPager`와 `TabLayout`을 활용해 스와이프 및 탭 네비게이션 구현.
   - Intent를 사용해 액티비티 간 데이터 전달.

---

## 기술적 세부 사항

### 데이터베이스 설계
- **Disease Model**:
  - 접종 권장 연령 및 금기사항을 이진값으로 저장하여 필터링 효율성 향상.
  - 예시:
    - 권장 연령 필터: `ageFilter`
    - 금기 기저질환 필터: `forbiddenHealthConditionFilter`

- **Disease Database**:
  - SQLite 기반으로 초기 실행 시 필수 데이터를 미리 삽입.
  - `DiseaseRepository`를 통해 데이터 조회 및 관리.

### 데이터 흐름
1. 사용자가 정보를 입력하면 `RecommendationActivity`에서 처리.
2. 입력 데이터는 Intent를 통해 `VaccineRecommendationActivity`로 전달.
3. `RecommendationService`에서 결과를 계산 후 화면에 표시.

---

## 팀 역할 분담

### 성형주 
- 데이터베이스 연동
- 외부 서버 이미지 다운로드
- 백신 추천 서비스 구현
- UI 업데이트
- 발표 자료 작성

### 이진솔 
- Jetpack Library(ViewPager, Fragment)를 이용한 화면 네비게이션 구현
- 화면 디자인 및 모든 페이지 구성
- Activity 3개 구현 및 Intent를 활용한 데이터 전달
- 발표 자료 정리

---

## 결론

이번 프로젝트를 통해 백신 추천 애플리케이션을 개발하며, 예방 접종의 중요성을 사용자들에게 알리고 맞춤형 정보를 제공하고자 했습니다. 간단한 사용자 입력만으로 신뢰할 수 있는 추천 정보를 제공하며, 특히 면역력이 약한 노인, 만성 질환 환자, 예방 접종 정보를 찾는 사용자들에게 유용하게 활용될 수 있습니다.

### 주요 성과:
- 백신 정보를 기반으로 효율적인 필터링 로직 구현.
- 직관적인 인터페이스 제공.
- 질병 정보 및 감염병 소식을 쉽게 확인 가능.


