USE purepoint;

-- 사용자 테이블 생성
CREATE TABLE User
(
    user_id         CHAR(36) PRIMARY KEY,               -- 회원 고유 식별자로 UUID 사용
    email           VARCHAR(255) NOT NULL UNIQUE,       -- 이메일 주소
    name            VARCHAR(100) NOT NULL,              -- 사용자 이름
    profile_picture VARCHAR(255),                       -- 프로필 사진 URL
    provider        VARCHAR(50),                        -- 소셜 로그인 제공자 (예: Google, Facebook)
    social_id       VARCHAR(255),                       -- 소셜 ID (소셜 플랫폼의 사용자 ID)
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP, -- 가입일 (현재 시간 기본값)
    password        VARCHAR(255)                        -- 비밀번호 (옵션, NULL 허용)
);

-- 소셜 로그인 테이블 생성
CREATE TABLE SocialLogin
(
    social_login_id INT PRIMARY KEY AUTO_INCREMENT, -- 소셜 로그인 고유 식별자
    user_id         CHAR(36),                       -- 사용자 ID (User 테이블의 외래 키로 UUID 사용)
    provider        VARCHAR(50),                    -- 소셜 제공자 (예: Google, Facebook)
    social_user_id  VARCHAR(255),                   -- 소셜 사용자 ID
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

-- 카테고리 테이블 생성
CREATE TABLE Category
(
    category_id   INT PRIMARY KEY,      -- 카테고리 고유 ID
    category_name VARCHAR(100) NOT NULL -- 카테고리 이름 (예: 클라우드, 데이터 분석)
);

-- 태그 테이블 생성
CREATE TABLE Tag
(
    tag_id INT PRIMARY KEY AUTO_INCREMENT, -- 태그 고유 식별자
    name   VARCHAR(100) NOT NULL UNIQUE    -- 태그 이름 (예: 클라우드, AI, 개발)
);

-- 비디오-태그 관계 테이블 생성
CREATE TABLE VideoTag
(
    video_id VARCHAR(20), -- 비디오 ID (Video 테이블 외래 키)
    tag_id   INT,         -- 태그 ID (Tag 테이블 외래 키)
    PRIMARY KEY (video_id, tag_id),
    FOREIGN KEY (video_id) REFERENCES Video (video_id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES Tag (tag_id) ON DELETE CASCADE
);

-- 사용자-영상 상호작용 테이블 생성
CREATE TABLE UserVideoInteraction
(
    interaction_id   INT PRIMARY KEY AUTO_INCREMENT,     -- 상호작용 고유 ID
    user_id          CHAR(36),                           -- 사용자 ID (User 테이블 외래 키)
    video_id         VARCHAR(20),                        -- 비디오 ID (Video 테이블 외래 키)
    action_type      ENUM ('view', 'like', 'save'),      -- 상호작용 유형 (조회, 좋아요, 저장)
    action_timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, -- 상호작용 시간
    FOREIGN KEY (user_id) REFERENCES User (user_id) ON DELETE CASCADE,
    FOREIGN KEY (video_id) REFERENCES Video (video_id) ON DELETE CASCADE
);



