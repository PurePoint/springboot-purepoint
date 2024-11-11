package com.purepoint.springbootpurepoint.community;

import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommReadPostResDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommUpdatePostReqDto;
import com.purepoint.springbootpurepoint.community.repository.CommunityRepository;
import com.purepoint.springbootpurepoint.community.service.CommunityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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


    @Test
    @DisplayName("게시글 작성 서비스 테스트")
    public void createPostTest() {

        // 게시글 생성 시 데이터 주입
        CommCreatePostReqDto requestDto = new CommCreatePostReqDto();
        requestDto.setUserId(UUID.randomUUID());
        requestDto.setPostTitle("테스트 제목");
        requestDto.setPostContent("테스트 내용");

        // 게시글 생성 메서드 호출
        Community createdPost = communityService.createPost(requestDto);

        // 생성된 게시글이 DB에 반영되었는지 조회
        Optional<Community> savedPost = communityRepository.findById(createdPost.getPostId());
        assertThat(savedPost).isPresent();
        assertThat(savedPost.get().getPostTitle()).isEqualTo(requestDto.getPostTitle());
        assertThat(savedPost.get().getPostContent()).isEqualTo(requestDto.getPostContent());

    }


    @Test
    @DisplayName("게시글 수정 서비스 테스트")
    public void updatePostTest() {

        // 게시글 생성
        CommCreatePostReqDto createRequestDto = new CommCreatePostReqDto();
        createRequestDto.setUserId(UUID.randomUUID());
        createRequestDto.setPostTitle("초기 제목");
        createRequestDto.setPostContent("초기 내용");

        Community createdPost = communityService.createPost(createRequestDto);

        // 생성된 게시글 내용 수정
        CommUpdatePostReqDto updateRequestDto = new CommUpdatePostReqDto();
        updateRequestDto.setPostId(createdPost.getPostId());
        updateRequestDto.setPostTitle("수정된 제목");
        updateRequestDto.setPostContent("수정된 내용");

        Community updatedPost = communityService.updatePost(updateRequestDto);

        // 수정된 내용이 DB 반영되었는지 검증
        Optional<Community> savedPost = communityRepository.findById(updatedPost.getPostId());
        assertThat(savedPost).isPresent();
        assertThat(savedPost.get().getPostTitle()).isEqualTo(updateRequestDto.getPostTitle());
        assertThat(savedPost.get().getPostContent()).isEqualTo(updateRequestDto.getPostContent());

    }

    @Test
    @DisplayName("게시글 전체 조회 서비스 테스트")
    public void getPostTest() {

        // 게시글 생성 시 데이터 주입
        List<Community> createdPost = new ArrayList<>();

        for(int i=0; i<3; i++) {
            CommCreatePostReqDto requestDto = new CommCreatePostReqDto();
            requestDto.setUserId(UUID.randomUUID());
            requestDto.setPostTitle("테스트 제목"+ i);
            createdPost.add(communityService.createPost(requestDto));
        }

        // 게시글 조회 메서드 호출
        List<CommReadPostResDto> savedPost = communityService.getPost();

        // 게시글 조회 결과 검증
        assertThat(savedPost).isNotEmpty();
        for(int i=0; i<createdPost.size(); i++) {
            assertThat(createdPost.get(i).getPostTitle()).isEqualTo(savedPost.get(i).getPostTitle());
        }

    }

    @Test
    @DisplayName("최신순 게시글 조회 서비스 테스트")
    public void getLatestPostTest() throws InterruptedException {
        // 게시글 생성 시 데이터 주입
        List<Community> createdPost = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            CommCreatePostReqDto requestDto = new CommCreatePostReqDto();
            requestDto.setUserId(UUID.randomUUID());
            requestDto.setPostTitle("테스트 제목" + i);
            createdPost.add(communityService.createPost(requestDto));
            Thread.sleep(100);
        }

        // 최신순 게시글 조회 메서드 호출
        List<CommReadPostResDto> savedPost = communityService.getLatestPost();

        // 게시글 조회 결과 검증 (최신순으로 정렬되었는지 확인)
        assertThat(savedPost).isNotEmpty();
        for(int i = 0; i < createdPost.size(); i++) {
            log.info("게시글 조회: " + createdPost.get(i).getPostAt());
            log.info("최신순 게시글 조회: " + savedPost.get(i).getPostAt());
        }
    }

    @Test
    @DisplayName("조회순(인기순) 게시글 조회 서비스 테스트")
    public void getPopularPostTest() {
        // 게시글 생성 시 데이터 주입
        List<Community> createdPost = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            CommCreatePostReqDto requestDto = new CommCreatePostReqDto();
            requestDto.setUserId(UUID.randomUUID());
            requestDto.setPostTitle("테스트 제목" + i);
            Community community = communityService.createPost(requestDto);

            community.setPostView(i);

            createdPost.add(community);
        }

        // 조회순(인기순) 게시글 조회 메서드 호출
        List<CommReadPostResDto> savedPost = communityService.getPopularPost();

        // 게시글 조회 결과 검증 (조회순(인기순)으로 정렬되었는지 확인)
        assertThat(savedPost).isNotEmpty();
        for(int i = 0; i < createdPost.size(); i++) {
            log.info("게시글 조회: " + createdPost.get(i).getPostView());
        }

        for(int i = 0; i < createdPost.size(); i++) {
            log.info("조회순 게시글 조회: " + savedPost.get(i).getPostView());
        }
    }

    @Test
    @DisplayName("게시글 삭제 서비스 테스트")
    public void deletePostTest() {
        // 게시글 생성 시 데이터 주입
        List<Community> createdPost = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            CommCreatePostReqDto requestDto = new CommCreatePostReqDto();
            requestDto.setUserId(UUID.randomUUID());
            requestDto.setPostTitle("테스트 제목" + i);
            Community community = communityService.createPost(requestDto);

            community.setPostView(i);

            createdPost.add(community);
        }

        // 작성된 게시글 조회
        for(int i = 0; i < createdPost.size(); i++) {
            log.info("삭제 전 게시글 조회: " + communityRepository.findById(createdPost.get(i).getPostId()));
        }

        // 게시글 삭제 메서드 호출
        communityService.deletePost(createdPost.get(0).getPostId());

        for(int i = 0; i < createdPost.size(); i++) {
            log.info("삭제 후 게시글 조회: " + communityRepository.findById(createdPost.get(i).getPostId()));
        }

    }

    // ToDo 게시글 디테일 조회 서비스 테스트

    // ToDo 댓글 조회 서비스 테스트

    // ToDo 새 댓글 생성, 수정, 삭제 서비스 테스트
}
