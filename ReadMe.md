# SurfBoard 
## CRUD 게시판  
- 사용법(changeable)  
1. /post/create?postId= -> 포스트작성
2. /post/read?postId= -> 포스트조회  
3. /post/delete?postId= -> 포스트삭제  
4. /post/update?postId= -> 포스트수정  
5. /post/list -> 포스트목록  

## 패치노트  
Current version v2.0.9  
 versionHistory  
v1 포스트 `작성,조회,수정,삭제,목록` 기능을 구현했습니다.  
v2 URL방식으로 명령어가 작동하도록 변경했습니다.  
v2.0.1 이전버전의 오류수정  
v2.0.2 기존파일 일부 리팩토링 및 오류수정  
v2.0.9 게시판 기능 추가/URL 파라미터를 사용해 포스트와 게시판을 호출하도록 변경 
  
## 트러블 슈팅
1. 문제를 직면  
- (v2) 유효하지 않은 URL입력시 작동 중지.  
- (v2) 작성글이 없을때 read,delete,update 명령어입력시 작동 중지.
2. 그에 대한 고민  
- 예외처리를 했으나 부족했던것 같음  
- 예외처리의 위치에 문제가 있었던것같음
3. 해결방안 모색  
- 잘못된 명령어에 대한 예외처리는 switch문에서 하도록 수정
- 포스트가 없을때 예외발생시키지 않도록 수정
4. 해결  
- `UrlNotFoundException` 대신 switch문에 default로 잘못입력된 명령어를 처리하도록 수정  
- `read,delete,update post` 메서드의 예외처리 제거, 포스트 리스트가 없을 때 알림 메세지 추가  
- 위의 두 문제에 대한 수정을 완료했습니다.
  
### Comment  
점점 늘어나는 변수들, 메서드들..  
구현해놓고 보니 너무 가독성과 확장성이 떨어집니다.
추후 관리편의성과 확장성을 위해 기능별 캡슐화와 리팩토링이 필요할것 같습니다.
