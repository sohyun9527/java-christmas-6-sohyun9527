# 🎄4주차 미션 - 크리스마스 프로모션

## 🎮 진행 과정

```
[출력] 시작 메세지
[출력] 방문 예상 날짜 요청 메세지
[입력] 방문 예상 날짜
    - [예외] 숫자가 아닐 경우 예외 발생
    - [예외] 1 ~ 31일 사이의 수가 아닐 경우 예외 발생

[출력] 주문할 메뉴, 개수 요정 메세지
[입력] 메뉴, 개수
    - [예외] 지정된 형식이 아닐 경우 예외 발생
      - 지정 형식
        - 메뉴와 메뉴 사이는 (,)로 구분한다
        - 메뉴와 개수 사이는 (-)로 구분한다
    - [예외] 메뉴의 총 합이 20개를 넘어갈 경우 예외 발생
    - [예외] 메뉴판에 없는 메뉴 주문 시 예외 발생

[기능] 금요일, 토요일이라면 주말, 아니라면 평일 할인
  - 주말: 메인 카테고리 각 2023원 할인
  - 평일: 디저트 카테고리 각 2023원 할인
[기능] 크리스마스 할인 : 1 ~ 25일
  - 1000원 시작, 매일 100원씩 증가
[기능] 특별 할인 : 일요일이거나 25일
  - 총 가격에서 1000원 할인
[기능] 증정 이벤트 : 할인 전 총 금액이 120,000원 이상이라면 샴페인 증정

[출력] 결과 출력
 
```

## 🚀 기능 구현 목록

### 입력 기능

- [x] 방문 예상 날짜를 입력받는다
- [x] 주문 메뉴, 개수를 입력받는다

### 출력 기능

- [x] 시작 메세지 출력
- [x] 방문 예상 날짜 요청 메세지 출력
- [x] 주문 메뉴, 개수 요청 메세지
- [x] 해당 날짜의 혜택 미리 보기 메세지 출력
- [x] 방문 예상 날짜 출력
- [x] 출력 형식에 맞춰 (#,###원) 출력 기능

### 예외 발생

- [x] 방문 날짜가 숫자가 아닌 경우
- [x] 방문 날짜가 1 ~ 31 사이가 아닐 경우
- [x] 메뉴판에 없는 메뉴를 입력하는 경우
- [x] 메뉴의 개수가 1 미만의 숫자일 경우
- [x] 메뉴 형식이 예시와 다른 경우(메뉴-숫자,메뉴-숫자 혹은 메뉴-숫자)
    - [x] 여러 메뉴를 (,)로 구분하지 않은 경우
    - [x] 하나의 메뉴를 (-)로 구분하지 않은 경우
- [x] 메뉴가 중복될 경우
- [x] 음료만 주문했을 경우
- [x] 메뉴의 총 합이 20개를 초과할 경우

### 할인 기능

- [x] 총 주문 금액이 10000원 이상인지 판별하는 기능

- 크리스마스 디데이 할인 - 25일 이전
- [x] 1000원 시작, 매일 100원 씩 더하는 기능

- 12월 총 이벤트 할인
- [x] 디저트 메뉴 1개당 2,2023원 차감 기능
- [x] 메인 메뉴 1개당 2,023원 차감 기능
- [x] 총 주문 금액에서 1,000원 차감 기능
- [x] 할인 전 총 금액이 12만원 이상 시, 샴페인 증정 기능

- 총 혜택금액에 따른 이벤트 배지 부여
- [x] 5000원 이상일 시 별 반환
- [x] 10000원 이상일 시 트리 반환
- [x] 20000원 이상일 시 산타 반환

### 이벤트 날짜 관련 기능

- [x] 25일 이전, 이후를 판별하는 기능
- [x] 금요일 or 토요일인지 판별하는 기능
- [x] 들어온 date 가 무슨 요일인지 판별하는 기능
- [x] 일요일 or 25일인지 판별하는 기능

### 주문 기능

- [x] 들어온 주문들을 객체로 생성하는 기능
- [x] 주문들이 담긴 리스트 생성
- [x] 디저트 카테고리의 총 개수를 세는 기능
    - [x] 디저트메뉴인지 판별하는 기능
- [x] 메인 카테고리의 총 개수를 세는 기능
    - [x] 메인메뉴인지 판별하는 기능
- [x] 주문의 총 가격을 반환하는 기능
