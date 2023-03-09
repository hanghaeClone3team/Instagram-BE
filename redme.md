![스크린샷 2023-03-09 오후 10 37 25](https://user-images.githubusercontent.com/111184537/224040677-eb5c332a-6506-49e2-9976-80f0ff3bac67.png)

---
# 1. 팀원 정보
| 이름 | Position | 기능 구현 | 깃허브 주소 |
| --- | --- | --- | --- |
| 김명주 | FE |  | https://github.com/oterte |
| 이인영 | FE |  | https://github.com/yeooong-dev |
| 박성민 | BE | 로그인, 회원가입, 팔로우 | https://github.com/seongminnnn |
| 한승현 | BE | 댓글, 대댓글, 채팅 | https://github.com/gkstmdgus |
| 박현아 | BE | 게시글, 해시태그 | https://github.com/aihtnyc-h |

--------

# 2. 기능명세서


| 페이지 | 페이지 구분 | 기능 | 세부기능 |
| --- | --- | --- | --- |
| signUp.html | /singup | 회원가입 | 아이디, 비밀번호 유효성/ 아이디 중복조회 |
| signIn.html | /login | 로그인 | 소셜 로그인(추가) |
|  | /logout | 로그아웃 | 로그아웃시 로그인 페이지로 이동 |
| content.html | /content | 게시글 상세보기 | 무한 스크롤(게시글 내용, 댓글), 게시글 수정(창을 띄우기) |
| content.html | /content | 댓글 등록 |  |
| content.html | /content | 댓글 수정, 삭제 |  |
| update.html | /write | 게시글 수정, 삭제 |  |
| write.html | /write | 게시글 작성 | - 이미지 첨부 |
| main.html | /main | 전체 글 목록 조회 |  |
|  |  | 게시글 좋아요 |  |
|  |  | 댓글 좋아요 |  |
|  |  | 동영상 업로드(10초) |  |
|  |  | 다이렉트 메세지(채팅) |  |
|  | /mypage | 내가 작성한 글 목록 조회 |  |
|  | /{username}page | 닉네임별 마이페이지, 회원정보, 프로필 사진 변경 | 기본사진 정해서 넣어야함  |
|  |  | 동영상 업로드 |  |
|  |  | 해쉬태그 |  |
|  |  |  |  |
|  |  | 회원탈퇴 |  |
|  |  | 팔로우 팔로잉 |  |


-------------

# 3. 아키텍쳐
![스크린샷 2023-03-09 오후 10 37 35](https://user-images.githubusercontent.com/111184537/224040873-0838cad4-af09-49af-97f7-57de52411e5d.png)

-------------

# 4. 와이어 프레임
![스크린샷 2023-03-09 오후 10 37 46](https://user-images.githubusercontent.com/111184537/224041053-92c9c270-b430-47da-8f13-52513c6190c3.png)
![스크린샷 2023-03-09 오후 10 37 55](https://user-images.githubusercontent.com/111184537/224041093-e8f84589-2aa3-415b-a2a4-047e35e7129e.png)

-------------

# 5. ERD
![스크린샷 2023-03-07 오후 11 13 55](https://user-images.githubusercontent.com/111184537/224043534-57790f60-5532-4e61-8a91-9220c9bef72f.png)


-------------

# 6. 기능구현
![스크린샷 2023-03-09 오후 10 39 16](https://user-images.githubusercontent.com/111184537/224041199-7cbdf99f-bc77-4936-8926-cc676dfbc491.png)
![스크린샷 2023-03-09 오후 10 39 25](https://user-images.githubusercontent.com/111184537/224041638-3d934a3e-b6ba-4e1c-bf74-fb4953cdb1cd.png)
![스크린샷 2023-03-09 오후 10 39 33](https://user-images.githubusercontent.com/111184537/224041671-bc419269-d38d-430d-a7d0-d53beb78a5cb.png)
![스크린샷 2023-03-09 오후 10 39 46](https://user-images.githubusercontent.com/111184537/224041699-514a8b6f-d963-4606-bafd-f47dcfb6b075.png)
![스크린샷 2023-03-09 오후 10 39 56](https://user-images.githubusercontent.com/111184537/224041727-8d552c8e-c0df-471a-9332-cdc7433d750f.png)

-------------

# 7. API 명세서
![스크린샷 2023-03-09 오후 8 35 07](https://user-images.githubusercontent.com/111184537/224042464-81d647ef-0065-4320-a3a1-6053124ec14c.png)
![스크린샷 2023-03-09 오후 8 35 36](https://user-images.githubusercontent.com/111184537/224042508-8993cb26-29f9-4fb5-a854-1c0e97da896b.png)
![스크린샷 2023-03-09 오후 8 35 55](https://user-images.githubusercontent.com/111184537/224042546-ff9aae48-3585-4019-86d5-fd57bfadec36.png)
![스크린샷 2023-03-09 오후 8 36 12](https://user-images.githubusercontent.com/111184537/224042580-f70e22eb-232f-48bc-b6cf-9f02ad73e771.png)

------------

# 8. 트러블 슈팅
![17](https://user-images.githubusercontent.com/111184537/224048574-91ef228e-7797-428c-90b2-9a1002073cda.jpg)
![18](https://user-images.githubusercontent.com/111184537/224048594-8d37a344-b90a-4c55-8d45-b4040fb80772.jpg)
![19](https://user-images.githubusercontent.com/111184537/224048604-bb50f7b0-b743-46be-8de2-ac100f7ab2c4.jpg)
![20](https://user-images.githubusercontent.com/111184537/224048618-a2ea8059-9333-4c3c-b40a-a3e1c2918467.jpg)

----

# 9. 결과물
홈페이지 주소 : [http://instagram-sparta.s3-website.ap-northeast-2.amazonaws.com/](http://instagram-sparta.s3-website.ap-northeast-2.amazonaws.com/)

시연 영상 :  [https://www.youtube.com/watch?v=p8W-KzFsETk&t=3s](https://www.youtube.com/watch?v=p8W-KzFsETk&t=3s)

BE

[https://github.com/hanghaeClone3team/Instagram-BE.git](https://github.com/hanghaeClone3team/Instagram-BE.git)

FE

[https://github.com/hanghaeClone3team/instagram-FE-EC2.git](https://github.com/hanghaeClone3team/instagram-FE-EC2.git)

---

### 채팅 필요사항

---

- 채팅방 구독 sub/chat/roomId
- 채팅방 발행 pub/chat/roomId
- 메시지를 보내면 메시지 목록에 저장하기
    
    → 필요한 것 : roomId, 작성자, 메시지
    

- DM 입장 시 팔로우된 사람들 목록 반환
- 채팅방 입장
    - 채팅방이 존재하는 여부 확인 (token → username 추출, PathVariable의 user_id로 찾기)
        - 있으면 그 채팅방 반환
        - 없으면 채팅방 생성 후 반환
    - 받고 프론트는 handshake, 구독하기
    - 내용 전달받으면 페이지에 뿌려주기
---

# 10. 주간 할일

|  | 3월 3(금) | 3월 4(토) | 3월 5일 (일) | 3월 6일(월) | 3월 7일(화) | 3월 8일(수) | 3월 9월(목) |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 박성민(BE) | 로그인, 로그아웃, 회원가입 | EC2 연결 | RDS, S3서버 연결 | S3서버 연결, 채팅 공부 | 팔로우, 페이지네이션 | 팔로우 연관관계, 페이지네이션 | 페이지 네이션팔로우 연관관계, 도커 공부 |
| 한승현(BE) | 댓글, 대댓글 CRUD  | 댓글 좋아요,채팅 공부 | 스톰프 예제 코드 작성 | 채팅에 레디스 적용 | 팔로우 된 사람만 DM방에 리스트로 반환 | 채팅방 생성 | 도커 공부 |
| 박현아(BE) | 기획 및 게시글 작성, 조회, 수정, 삭제, 좋아요, 이미지까지 코드작성 완료(내일 수정 예정) | 포스트맨 확인완료(작동함)게시글 조회에 댓글, 팔로잉, 좋아요 넣어주기 | 게시글 대댓글 넣기 완료 ec2, rds, s3 연결 웹소캣 완성 | 웹소캣 → 스톰프 적용(실패) | 웹소캣 → 스톰프 적용(실패) 해시태그 및 검색 엔티티 수정 | 해시태그 및 검색 로직 완성 (DB 설계)(실패) | JPA DB 로직 고민 독커 공부 |
| 김명주(FE) | 게시물 컴포넌트, 사이드바 컴포넌트 생성 및 css 추가 | FormData 다루는 법 공부게시물 생성 및 조회 구현 | 상세페이지, 수정페이지 추가 게시물 삭제 기능 구현 | FormData 수정 방법 공부 게시물 댓글 생성 및 삭제 조회 api 완성 | 게시물 수정 기능 구현 게시물 댓글 생성 및 삭제 조회 api 완성 | 무한스크롤 공부 게시물 관련 페이지 css 추가 | 게시물 관련 페이지 css 보완 |
| 이인영(FE) | 로그인, 로그아웃 | 로그인, 로그아웃 Css추가 | 로그인, 로그아웃 연결시도카카오 로그인 Css추가 | 카카오 로그인 Css추가 | 마이페이지  | 카카오로그인 배포  | 배포 Css마무리 |
