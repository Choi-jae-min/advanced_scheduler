## 일정관리 앱 (숙련)
### API 명세서

Server_Url = localhost:8080

#### 일정
| 기능       | Method | URL | 설명|
|----------|--------|--------------------|--------------|
| 일정 등록    | POST   | /api/schedulers| 일정을 등록합니다. |
| 일정 조회    | GET    | /api/schedulers/{id} | 하나의 일정을 조회합니다. |
| 일정 리스트 조회 | GET    | /api/schedulers | 조건에 맞는 일정리스트를 조회합니다. |
| 일정 수정    | PATCH    | /api/schedulers/{id} | 특정 일정의 정보를 수정합니다. |
| 일정 삭제    | DELETE | /api/schedulers/{id} | 특정 일정을 삭제합니다. |
