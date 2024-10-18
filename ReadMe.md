# SurfBoard 
## CRUD 게시판  
- 사용법(changeable)  
1. /post/create -> 포스트작성  
2. /post/read -> 포스트조회  
3. /post/delete -> 포스트삭제  
4. /post/update -> 포스트수정  
5. /post/list -> 포스트목록  

## 세부사항  
현재 2단계  
 단계별요구사항  
1. 포스트 `작성,조회,수정,삭제,목록` 기능구현
  
## 트러블 슈팅 (적어도 2개)  
1. 문제를 직면  
- (단계2-1) 유효하지 않은 URL입력시 작동 중지.  
- (단계2-1) 작성글이 없을때 read,delete,update 명령어입력시 작동 중지.
2. 그에 대한 고민  
- 예외처리를 했으나 부족했던것 같음  
3. 해결방안 모색  
- 잘못된 명령어에 대한 예외처리는 switch문에서 하도록 수정
- 포스트가 없을때 예외발생시키지 않도록 수정
4. 해결  
- `UrlNotFoundException` 대신 switch문에 default로 잘못입력된 명령어를 처리하도록 수정  
- `read,delete,update post` 메서드의 예외처리 제거, 포스트가 없을 때 알림 메세지 추가  
- 위의 두 문제 수정 완료