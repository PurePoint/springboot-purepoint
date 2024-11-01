# springboot-purepoint

프로젝트 이름: 미정

타켓층 : Youtube를 활용하여 IT 공부하는 사람들

활용하는 외부 API : Youtube Data API v3

배치를 사용해 주기적으로 API를 호출하고 데이터를 데이터베이스 갱신합니다.

실시간으로 외부 API를 요청하고 처리하는 동기화 서비스가 아닌 배치 처리를 하는 이유는 세가지 장점이 있습니다.

1. 일정한 API 호출 : 외부 API 의 호출을 일정하게 유지 하여 API 요금이나 제한에 걸릴 위험을 낮출 수 있습니다.
2. 성능상 이점 : 사용자가 API를 직업 호출하지 않고 데이터베이스에서 데이터를 조회하기 때문에, 일정한 성능을 보장합니다.
3. 안정성 : 유튜브 API 의 변동이나 일시적 오류에도 서비스의 일관성을 유지할 수 있습니다.


사용 라이브러리와 목적

- Spring Data JPA : 기본적인 CRUD를 간단하게 처리합니다.
- QueryDSL : 복잡한 동적 쿼리를 처리합니다.
- MapStruct : Entitiy와 Dto의 전환을 자동화합니다.
- Lombok : 생성자, 등과 같은 로직 반복을 최소화합니다.

```shelle
/batch // 배치 관련 클래스들 
/config // 배치 설정 파일들 BatchConfiguration.java 
/job // 배치 작업(Job) 관련 클래스들 UserJob.java 
/step // 배치 단계(Step) 관련 클래스들 UserStep.java 
/listener // 배치 리스너 클래스들 JobCompletionListener.java
```