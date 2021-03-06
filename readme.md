# 요청 사항
1. 정해진 시간 부터 거래가 가능하다
2. 다수의 투자자가 동시에 거래가 가능하다.
3. 총 투자금액 달성시 거래를 할 수 없다.
4. 가능한 시간대의 거래만 조회가 되어야 한다.
   1. 응답 : 상품ID, 상품제목, 총 모집금액, 투자자 수, 투자모집 상태, 모집 기간
5. 요청자의 아이디를 header에 X-USER-ID로 전달이 된다.
6. 투자
   1. 요청 : 사용자 식별값, 상품ID, 투자금액
   2. 모집 금액이 초과시 거절
   3. 거래 가능 일자 체크
7. 투자 내역 조회
   1. 내가 투자한 거래 내역을 조회
   2. 응답 : 상품ID, 상품제목, 총 모집금액, 나의 투자금액, 투자 일시

# 문제 해결 전략
1. DB처리 방식은 JPA와 QueryDSL을 사용한다.
2. REST API의 요청 전문 체크는 Validate를 사용한다.
3. 거래의 동시성은 Transactional를 사용하여 제어를 한다.
   1. 거래 데이터를 입력 바로 전에 가능 금액 체크
4. 응답은 공통 처리를 한다.

# 추가 해결할 사항
1. 실제로 동시성 처리가 되는지 확인 필요
2. getAbleTrade 최적화 필요
3. REST API테스트 필요