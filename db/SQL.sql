USE purepoint;

-- 사용자 테이블 생성
CREATE TABLE User
(
    user_id         CHAR(36) PRIMARY KEY,               -- 회원 고유 식별자로 UUID 사용
    email           VARCHAR(255) NOT NULL UNIQUE,       -- 이메일 주소
    name            VARCHAR(100) NOT NULL,              -- 사용자 이름
    profile_picture VARCHAR(255),                       -- 프로필 사진 URL
    social_id       VARCHAR(255),                       -- 소셜 ID (소셜 플랫폼의 사용자 ID)
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP, -- 가입일 (현재 시간 기본값)
    password        VARCHAR(255)                        -- 비밀번호 (옵션, NULL 허용)
);

-- 소셜 로그인 테이블 생성
CREATE TABLE SocialUser
(
    social_id       CHAR(36) PRIMARY KEY,           -- 소셜 로그인 고유 식별자
    user_id         CHAR(36),                       -- 사용자 ID (User 테이블의 외래 키로 UUID 사용)
    provider        VARCHAR(50),                    -- 소셜 제공자 (예: Google, Facebook)
    social_user_id  VARCHAR(255),                   -- 소셜 사용자 ID -> 소셜 서비스에서 제공하는
    token           VARCHAR(255),                   -- 액세스 토큰 (옵션, NULL 허용)
    FOREIGN KEY (user_id) REFERENCES User (user_id) -- User 테이블과의 외래 키 제약조건
);


-- 비디오 테이블 생성
CREATE TABLE Video
(
    video_id      VARCHAR(20) PRIMARY KEY,           -- 유튜브 영상의 고유 ID
    title         VARCHAR(255) NOT NULL,             -- 영상 제목
    description   TEXT,                              -- 영상 설명
    published_at  DATETIME,                          -- 영상 게시 시간
    channel_id    VARCHAR(20),                       -- 채널 ID
    channel_title VARCHAR(255),                      -- 채널 이름
    category_id   INT,                               -- 카테고리 ID (Category 테이블의 외래 키)
    duration      VARCHAR(20),                       -- 영상 길이 (ISO 8601 형식)
    definition    VARCHAR(10),                       -- 화질
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP -- 데이터 생성 시간
);

-- 태그 그룹 테이블 생성
CREATE TABLE TagGroup
(
    tag_group_id   INT PRIMARY KEY,      -- 태그 그룹 고유 ID
    tag_group_name VARCHAR(100) NOT NULL -- 태그 그룹 이름 (예: 클라우드, 데이터 분석)
);

-- 태그 테이블 생성
CREATE TABLE Tag
(
    tag_id INT PRIMARY KEY AUTO_INCREMENT,      -- 태그 고유 식별자
    tag_group_id INT,                           -- 태그 그룹 고유 식별자
    tag_name   VARCHAR(100) NOT NULL UNIQUE     -- 태그 이름 (예: 클라우드, AI, 개발)
);

-- 비디오-태그 관계 테이블 생성
CREATE TABLE VideoTag
(
    video_id VARCHAR(20),                       -- 비디오 ID (Video 테이블 외래 키)
    tag_id   INT,                               -- 태그 ID (Tag 테이블 외래 키)
    PRIMARY KEY (video_id, tag_id),
    FOREIGN KEY (video_id) REFERENCES Video (video_id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES Tag (tag_id) ON DELETE CASCADE
);

# 시청 기록
CREATE TABLE VideoRecord
(
    video_id VARCHAR(20), -- 비디오 ID
    user_id CHAR(36), -- 유저 ID
    -- 할 수 있으면 재생 시간(영상 중단한 시간)
    PRIMARY KEY (video_id, user_id),
    FOREIGN KEY (video_id) REFERENCES Video (video_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES User (user_id) ON DELETE CASCADE
);

-- 비디오 폴더(컬렉션)
CREATE TABLE Folder
(
    folder_id CHAR(36) PRIMARY KEY AUTO_INCREMENT, -- 폴더 ID
    folder_name VARCHAR(255) NOT NULL, -- 폴더 이름
    folder_created_at DATETIME DEFAULT CURRENT_TIMESTAMP, # 생성일
    folder_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP, # 수정일
    user_id CHAR(36), -- 유저 ID(폴더 주인)
    FOREIGN KEY (user_id) REFERENCES User (user_id) ON DELETE CASCADE
);

-- 폴더 비디오
CREATE TABLE FolderVideo
(
    folder_id CHAR(36), # 폴더 ID
    재생목록_id VARCHAR(20), # 비디오 ID
    PRIMARY KEY (folder_id, 재생목록_id),
    FOREIGN KEY (folder_id) REFERENCES Folder (folder_id) ON DELETE CASCADE,
    FOREIGN KEY (재생목록_id) REFERENCES 재생목록 (재생목록_id) ON DELETE CASCADE
);


#게시판
CREATE TABLE Board (
   board_id CHAR(36) AUTO_INCREMENT PRIMARY KEY,    #게시글 ID
   user_id VARCHAR(255),   #게시글 작성자 ID
   post_title VARCHAR(100),    #게시글 제목
   post_contents VARCHAR(255), #게시글 내용
   post_views INT, #게시글 조회수
   post_at DATETIME,   #게시글 작성일
   post_update_at DATETIME, #게시글 수정일
   FOREIGN KEY(user_id) REFERENCES User (user_id)
);

#게시글 이미지
CREATE TABLE BoardImage (
    board_image_id CHAR(36) AUTO_INCREMENT PRIMARY KEY,  #게시글 이미지 ID
    board_id INT,    #게시글 ID
    board_image VARCHAR(100),    #게시글 이미지 URL
    FOREIGN KEY(board_id) REFERENCES Board (board_id)
);

#신고
CREATE TABLE Report (
    report_id CHAR(36) PRIMARY KEY, #신고된 게시글, 댓글, 대댓글 ID
    board_id INT, #게시글 ID
    user_id INT, #신고한 사용자 ID
    report_user_id INT, #신고 당한 사용자 ID
    report_at DATETIME, #신고일
    report_type_id  TINYINT,    #신고 유형
    report_status  BOOLEAN,    #신고 상태(true: 해결 / false: 미해결)
    report_contents VARCHAR(100),   #신고 사유
    FOREIGN KEY(board_id) REFERENCES Board (board_id),
    FOREIGN KEY(user_id) REFERENCES User (user_id),
    FOREIGN KEY(report_type_id) REFERENCES ReportType (report_type_id)
);

CREATE TABLE ReportType (
    report_type_id TINYINT PRIMARY KEY, #1: 게시글, 2: 댓글, 3: 대댓글
    report_name VARCHAR(25) #신고 유형
);