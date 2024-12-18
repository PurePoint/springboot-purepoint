package com.purepoint.springbootpurepoint.community;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreateCommentReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommDeletePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommPagingResDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommUpdatePostReqDto;
import com.purepoint.springbootpurepoint.community.mapper.CommunityMapper;
import com.purepoint.springbootpurepoint.community.repository.CommentRepository;
import com.purepoint.springbootpurepoint.community.repository.CommunityRepository;
import com.purepoint.springbootpurepoint.community.service.CommunityServiceImpl;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
public class CommunityServiceTest {

    @Autowired
    private CommunityServiceImpl communityService;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityMapper communityMapper = CommunityMapper.INSTANCE;


    @Test
    @DisplayName("게시글 작성 서비스 테스트")
    public void createPostTest() {
        // 게시글 생성 시 데이터 주입
        CommCreatePostReqDto requestDto = CommCreatePostReqDto.builder()
                .videoId("-JHAUaOq6l0")
                .playlistId(null)
                .userId(UUID.fromString(("ce4bb5a1-5ffe-4137-bdfc-c4959c184a7e")))
                .postTitle("테스트 제목")
                .postContent("테스트 내용")
                .build();

        log.info("requestDto : {}", requestDto.getUserId());
        
        // 게시글 생성 메서드 호출
        communityService.createPost(requestDto);

        log.info("createPost : {}", communityRepository.findAll());

    }


    @Test
    @DisplayName("게시글 수정 서비스 테스트")
    public void updatePostTest() {

        // 게시글 생성
        CommCreatePostReqDto requestDto = CommCreatePostReqDto.builder()
                .videoId("-JHAUaOq6l0")
                .playlistId(null)
                .userId(UUID.fromString(("ce4bb5a1-5ffe-4137-bdfc-c4959c184a7e")))
                .postTitle("테스트 제목")
                .postContent("테스트 내용")
                .build();

        Community createdPost = communityService.createPost(requestDto);

        log.info("createPost : {}", communityRepository.findAll());
        log.info("getPostId : {}", createdPost.getPostId());
        log.info("getPostTitle : {}", createdPost.getPostTitle());
        log.info("getPostContent : {}", createdPost.getPostContent());

        // 생성된 게시글 내용 수정
        CommUpdatePostReqDto updateRequestDto = CommUpdatePostReqDto.builder()
                .postId(createdPost.getPostId())
                .userId(UUID.fromString(("ce4bb5a1-5ffe-4137-bdfc-c4959c184a7e")))
                .postTitle("수정된 제목")
                .postContent("수정된 내용")
                .build();

        Community updatedPost = communityService.updatePost(updateRequestDto);

        log.info("getPostId: {}", updatedPost.getPostId());

        // 수정된 내용이 DB 반영되었는지 검증
        log.info("createPost : {}", updatedPost);
        Optional<Community> savedPost = communityRepository.findById(updatedPost.getPostId());
        assertThat(savedPost).isPresent();
        log.info("getPostId : {}", savedPost.get().getPostId());
        log.info("getPostTitle : {}", savedPost.get().getPostTitle());
        log.info("getPostContent : {}", savedPost.get().getPostContent());

    }

    @Test
    @DisplayName("게시글 전체 조회 서비스 테스트")
    public void getPostTest() {
        // 게시글 생성 시 데이터 주입
        List<Community> createdPost = new ArrayList<>();

        for(int i=0; i<3; i++) {
            CommCreatePostReqDto requestDto = CommCreatePostReqDto.builder()
                    .videoId("-JHAUaOq6l0")
                    .playlistId(null)
                    .userId(UUID.fromString("ce4bb5a1-5ffe-4137-bdfc-c4959c184a7e"))
                    .postTitle("테스트 제목"+ i)
                    .postContent("테스트 내용"+ i)
                    .build();

            createdPost.add(communityService.createPost(requestDto));
        }

        // 게시글 조회 메서드 호출
        CommPagingResDto savedPost = communityService.getPost(createdPost.get(0).getVideoId(), 1, 5);

        // 게시글 조회 결과 검증
        for(int i=0; i<savedPost.getContent().size(); i++) {
            log.info("getPostAt : {}", savedPost.getContent().get(i).getPostAt());
            log.info("getNickname : {}", savedPost.getContent().get(i).getNickname());
            log.info("getPostTitle : {}", savedPost.getContent().get(i).getPostTitle());
            log.info("getPostContent : {}", savedPost.getContent().get(i).getPostContent());
            log.info("getPostUpdateAt : {}", savedPost.getContent().get(i).getPostUpdateAt());
            if(savedPost.getContent().get(i).getComments() != null) {
                for(int j=0; j < savedPost.getContent().get(i).getComments().size(); j++) {
                    log.info("getCommentNickname : {}", savedPost.getContent().get(i).getComments().get(j).getCommentNickname());
                    log.info("getProfileImage : {}", savedPost.getContent().get(i).getComments().get(j).getProfileImage());
                    log.info("getCommentContents : {}", savedPost.getContent().get(i).getComments().get(j).getCommentContents());
                    log.info("getCommentAt : {}", savedPost.getContent().get(i).getComments().get(j).getCommentAt());
                    log.info("getCommentUpdateAt : {}", savedPost.getContent().get(i).getComments().get(j).getCommentUpdateAt());
                    log.info("getCommentDeleteAt : {}", savedPost.getContent().get(i).getComments().get(j).getCommentDeleteAt());
                }
            }

        }

        log.info("getPageSize : {}", savedPost.getPageSize());
        log.info("getCurrentPage : {}", savedPost.getCurrentPage());
        log.info("getTotalPages : {}", savedPost.getTotalPages());
        log.info("getTotalElements : {}", savedPost.getTotalElements());


    }


    @Test
    @DisplayName("게시글 삭제 서비스 테스트")
    public void deletePostTest() {
        // 게시글 생성 시 데이터 주입
        List<Community> createdPost = new ArrayList<>();

        for(int i=0; i<3; i++) {
            CommCreatePostReqDto requestDto = CommCreatePostReqDto.builder()
                    .videoId("-JHAUaOq6l0")
                    .playlistId(null)
                    .userId(UUID.fromString("ce4bb5a1-5ffe-4137-bdfc-c4959c184a7e"))
                    .postTitle("테스트 제목"+ i)
                    .postContent("테스트 내용"+ i)
                    .build();

            createdPost.add(communityService.createPost(requestDto));
        }

        // 작성된 게시글 조회
        for (Community community : createdPost) {
            Optional<Community> optionalCommunity = communityRepository.findById(community.getPostId());

            optionalCommunity.ifPresent(
                    value -> log.info("삭제 전 게시글 조회: {}, {}, {}", value.getPostId(), value.getUserId(), value.getPostDeleteAt())
            );
        }

        CommDeletePostReqDto commDeletePostReqDto = CommDeletePostReqDto.builder()
                .postId(createdPost.get(0).getPostId())
                .userId(createdPost.get(0).getUserId())
                .build();

        // 게시글 삭제 메서드 호출
        communityService.deletePost(commDeletePostReqDto);

        // 작성된 게시글 조회
        for (Community community : createdPost) {
            Optional<Community> optionalCommunity = communityRepository.findById(community.getPostId());

            optionalCommunity.ifPresent(
                    value -> log.info("삭제 후 게시글 조회: {}, {}, {}", value.getPostId(), value.getUserId(), value.getPostDeleteAt())
            );
        }

    }

//    @Test
//    @DisplayName("최신순 게시글 조회 서비스 테스트")
//    public void getLatestPostTest() throws InterruptedException {
//        // 게시글 생성 시 데이터 주입
//        List<Community> createdPost = new ArrayList<>();
//
//        for (int i = 0; i < 3; i++) {
//            CommCreatePostReqDto requestDto = CommCreatePostReqDto.builder()
//                    .userId(UUID.randomUUID())
//                    .postTitle("테스트 제목" + i)
//                    .build();
//
//            createdPost.add(communityService.createPost(requestDto));
//            Thread.sleep(100);
//        }
//
//        // 최신순 게시글 조회 메서드 호출
//        List<CommReadPostResDto> savedPost = communityService.getLatestPost();
//
//        // 게시글 조회 결과 검증 (최신순으로 정렬되었는지 확인)
//        assertThat(savedPost).isNotEmpty();
//        for(int i = 0; i < createdPost.size(); i++) {
//            log.info("게시글 조회: " + createdPost.get(i).getPostAt());
//            log.info("최신순 게시글 조회: " + savedPost.get(i).getPostAt());
//        }
//    }

//    @Test
//    @DisplayName("조회순(인기순) 게시글 조회 서비스 테스트")
//    public void getPopularPostTest() {
//        // 게시글 생성 시 데이터 주입
//        List<Community> createdPost = new ArrayList<>();
//
//        for (int i = 0; i < 3; i++) {
//            CommCreatePostReqDto requestDto = CommCreatePostReqDto.builder()
//                    .userId(UUID.randomUUID())
//                    .postTitle("테스트 제목" + i)
//                    .build();
//            Community community = communityService.createPost(requestDto);
//
//            Community.builder()
//                    .postView(i)
//                    .build();
//
//            createdPost.add(community);
//        }
//
//        // 조회순(인기순) 게시글 조회 메서드 호출
//        List<CommReadPostResDto> savedPost = communityService.getPopularPost();
//
//        // 게시글 조회 결과 검증 (조회순(인기순)으로 정렬되었는지 확인)
//        assertThat(savedPost).isNotEmpty();
//        for(int i = 0; i < createdPost.size(); i++) {
//            log.info("게시글 조회: " + createdPost.get(i).getPostView());
//        }
//
//        for(int i = 0; i < createdPost.size(); i++) {
//            log.info("조회순 게시글 조회: " + savedPost.get(i).getPostView());
//        }
//    }


//    @Test
//    @DisplayName("게시글 디테일 조회 서비스 테스트")
//    public void getDetailPostTest() {
//
//        // 사용자 생성
//        User user = new User();
//        UUID userId = UUID.randomUUID();
//        user.setUserId(userId);
//        user.setEmail("test@test.com");
//        user.setNickname("test");
//        user.setProfileImage("test");
//        userRepository.save(user);
//
//        List<User> createUser = userRepository.findAll();
//        log.info("유저 ID: " + createUser.get(0).getUserId());
//
//        // 게시글 생성 시 데이터 주입
//        CommCreatePostReqDto commCreatePostReqDto = CommCreatePostReqDto.builder()
//                .userId(createUser.get(0).getUserId())
//                .postTitle("테스트 제목")
//                .postContent("테스트 내용")
//                .build();
//
//        // 게시글 생성 메서드 호출
//        Community createdPost = communityService.createPost(commCreatePostReqDto);
//
//        // 댓글 생성 시 데이터 주입
//        CommCreateCommentReqDto commCreateCommentReqDto = CommCreateCommentReqDto.builder()
//                .userId(createdPost.getUser().getUserId())
//                .postId(createdPost.getPostId())
//                .commentContents("테스트 내용")
//                .build();
//
//        // 댓글 생성 메서드 호출
//        communityService.createComment(commCreateCommentReqDto);
//
//        // 게시글 디테일 조회 메서드 호출
//        Community community = communityRepository.findWithCommentsAndUserByPostId(createdPost.getPostId());
//        log.info("getPostContent: " + community.getPostContent());
//        log.info("getUser: " + community.getUser().getNickname());
//        CommDetailPostResDto commDetailPostResDto = communityMapper.detailPostToDto(community);
//        log.info("commDetailPostResDto: " + commDetailPostResDto);
//        CommDetailPostResDto commDetailPostResDto = communityService.getDetailPost(createdPost.getPostId());

//        log.info("게시글 제목: " + commDetailPostResDto.getPostTitle());
//        log.info("게시글 작성자: " + commDetailPostResDto.getPostNickname());
//        log.info("게시글 내용: " + commDetailPostResDto.getPostContent());
//
//        log.info("댓글 depth: " + commDetailPostResDto.getDepth());
//        log.info("댓글 작성자: " + commDetailPostResDto.getCommentNickname());
//        log.info("댓글 직성자 프로필 이미지: " + commDetailPostResDto.getProfileImage());
//        log.info("댓글 내용: " + commDetailPostResDto.getCommentContents());

//    }

    // ToDo 댓글 조회 서비스 테스트


    @Test
    @DisplayName("댓글 작성 서비스 테스트")
    public void createCommentTest() {
        // 댓글 생성 시 데이터 주입
        CommCreateCommentReqDto commCreateCommentReqDto = CommCreateCommentReqDto.builder()
                .userId(UUID.fromString("ce4bb5a1-5ffe-4137-bdfc-c4959c184a7e"))
                .postId(UUID.fromString("428627d5-b193-4407-999b-db35eb0a766d"))
                .commentContents("댓글 테스트 내용")
                .parentCommentId(null)
                .build();

        // 댓글 생성 메서드 호출
        Comment createdComment = communityService.createComment(commCreateCommentReqDto);

        // 생성된 댓글이 DB에 반영되었는지 조회
        Optional<Comment> savedComment = commentRepository.findById(createdComment.getCommentId());
        assertThat(savedComment).isPresent();
        log.info("getCommentId: {}", savedComment.get().getCommentId());
        log.info("getCommentContents: {}", savedComment.get().getCommentContents());
        log.info("getParentCommentId: {}", savedComment.get().getParentCommentId());
        log.info("getUser: {}", savedComment.get().getUser());
    }

    @Test
    @DisplayName("댓글 조회 테스트")
    public void getCommentTest() {

        List<Comment> savedComment = commentRepository.findByPostId(UUID.fromString("428627d5-b193-4407-999b-db35eb0a766d"), Sort.by(Sort.Order.asc("commentAt")));

        for(int i=0; i<savedComment.size(); i++) {
            log.info("getCommentId{}: {}", i, savedComment.get(i).getCommentId());
            log.info("getCommentContents{}: {}", i, savedComment.get(i).getCommentContents());
            log.info("getParentCommentId{}: {}", i, savedComment.get(i).getParentCommentId());
            log.info("getNickname{}: {}", i, savedComment.get(i).getUser().getNickname());
            log.info("getProfileImage{}: {}", i, savedComment.get(i).getUser().getProfileImage());
        }

    }

    // ToDo 새 댓글 수정, 삭제 서비스 테스트
}
