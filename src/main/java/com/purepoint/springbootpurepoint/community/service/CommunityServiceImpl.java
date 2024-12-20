package com.purepoint.springbootpurepoint.community.service;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.*;
import com.purepoint.springbootpurepoint.community.dto.response.CommCommentResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommPagingResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommReadPostResDto;
import com.purepoint.springbootpurepoint.community.exception.CommentException;
import com.purepoint.springbootpurepoint.community.exception.PostException;
import com.purepoint.springbootpurepoint.community.exception.PostProcessingException;
import com.purepoint.springbootpurepoint.community.mapper.CommentMapper;
import com.purepoint.springbootpurepoint.community.mapper.CommunityMapper;
import com.purepoint.springbootpurepoint.community.repository.CommentRepository;
import com.purepoint.springbootpurepoint.community.repository.CommunityRepository;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.exception.UserNotFoundException;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 커뮤니티 서비스 구현체
 * <p>게시글과 댓글 관련 주요 비즈니스 로직을 담당합니다.</p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final CommunityMapper communityMapper = CommunityMapper.INSTANCE;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;

    /**
     * 게시글 생성
     *
     * @param commCreatePostReqDto 게시글 생성 요청 DTO
     * @return 생성된 게시글 엔티티
     */
    @Override
    public Community createPost(CommCreatePostReqDto commCreatePostReqDto) {
        userRepository.findById(commCreatePostReqDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + commCreatePostReqDto.getUserId()));

        Community community = communityMapper.createPostToEntity(commCreatePostReqDto);

        return communityRepository.save(community);
    }

    /**
     * 게시글 수정
     *
     * @param commUpdatePostReqDto 게시글 수정 요청 DTO
     * @return 수정된 게시글 엔티티
     */
    @Override
    public Community updatePost(CommUpdatePostReqDto commUpdatePostReqDto) {
        Community community = communityRepository.findById(commUpdatePostReqDto.getPostId())
                .orElseThrow(() -> new PostException("Post not found with id: " + commUpdatePostReqDto.getPostId()));

        userRepository.findById(commUpdatePostReqDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + commUpdatePostReqDto.getUserId()));

        community = communityMapper.updatePostToEntity(community, commUpdatePostReqDto);

        return communityRepository.save(community);
    }

    /**
     * 게시글 조회
     *
     * @param videoId 비디오 ID
     * @param page    페이지 번호
     * @param size    페이지 크기
     * @return 게시글 페이징 응답 DTO
     */
    @Override
    public CommPagingResDto getPost(String videoId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Order.desc("postAt")));

        Page<Community> community = communityRepository.findAllByVideoIdAndPostDeleteAtIsNull(videoId, pageable);

        if(community.isEmpty()) {
            throw new PostException("Post not fount with videoId and postDeleteAt is null: " + videoId);
        }

        // 닉네임 정보 추출
        List<String> nicknames = community.stream()
                .map(post -> {
                    return userRepository.findById(post.getUserId())
                            .map(User::getNickname)
                            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + post.getUserId()));
                })
                .toList();

        // 댓글 정보 추출
        Map<UUID, List<CommCommentResDto>> allCommCommentResDtos = community.stream()
                .collect(Collectors.toMap(
                        Community::getPostId,
                        post -> {
                            List<Comment> comments = commentRepository.findByPostId(post.getPostId(), Sort.by(Sort.Order.asc("commentAt")));
                            return comments.stream()
                                    .map(this::mapToCommCommentResDto)
                                    .collect(Collectors.toList());
                        }
                ));

        List<CommReadPostResDto> commReadPostResDtos = toCommReadPostResDto(community.getContent(), nicknames, allCommCommentResDtos);

        return communityMapper.toCommPagingDto(commReadPostResDtos, community);
    }

    /**
     * 댓글 객체를 CommCommentResDto 객체로 변환합니다.
     *
     * @param comment 변환할 댓글 객체
     * @return 변환된 CommCommentResDto 객체
     * @throws CommentException 댓글 변환에 실패한 경우 발생
     */
    private CommCommentResDto mapToCommCommentResDto(Comment comment) {
        CommCommentResDto commCommentResDto = commentMapper.toCommCommentResDto(comment);
        if(commCommentResDto == null) {
            throw new CommentException("Failed to map comment to commCommentResDto: " + comment);
        }
        return commCommentResDto;
    }

    /**
     * 커뮤니티 게시물 정보를 CommReadPostResDto 리스트로 변환합니다.
     *
     * @param communities 커뮤니티 게시물 목록
     * @param nicknames 댓글 작성자의 닉네임 목록
     * @param allCommCommentResDtos 게시물에 대한 댓글 DTO 리스트 맵
     * @return 변환된 CommReadPostResDto 목록
     * @throws PostProcessingException 커뮤니티 게시물 처리에 실패한 경우 발생
     */
    private List<CommReadPostResDto> toCommReadPostResDto(List<Community> communities, List<String> nicknames, Map<UUID, List<CommCommentResDto>> allCommCommentResDtos) {
        List<CommReadPostResDto> commReadPostResDtos = communityMapper.toCommReadPostResDto(communities, nicknames, allCommCommentResDtos);
        if(commReadPostResDtos.isEmpty()) {
            throw new PostProcessingException("Failed to process community post: " + communities + nicknames + commReadPostResDtos);
        }
        return commReadPostResDtos;
    }

    /**
     * 게시글 삭제
     *
     * @param commDeletePostReqDto 게시글 삭제 요청 DTO
     * @return 삭제 처리된 게시글 엔티티
     */
    @Override
    public Community deletePost(CommDeletePostReqDto commDeletePostReqDto) {
        Community community = communityRepository.findById(commDeletePostReqDto.getPostId())
                .orElseThrow(() -> new PostException("Post not found with id: " + commDeletePostReqDto.getPostId()));

        userRepository.findById(commDeletePostReqDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + commDeletePostReqDto.getUserId()));

        community = communityMapper.deletePostToEntity(community);
        return communityRepository.save(community);
    }

    /**
     * 댓글 생성
     *
     * @param commCreateCommentReqDto 댓글 생성 요청 DTO
     * @return 생성된 댓글 엔티티
     */
    @Override
    public Comment createComment(CommCreateCommentReqDto commCreateCommentReqDto) {
        communityRepository.findById(commCreateCommentReqDto.getPostId())
                .orElseThrow(() -> new PostException("Post not found with id: " + commCreateCommentReqDto.getPostId()));

        userRepository.findById(commCreateCommentReqDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + commCreateCommentReqDto.getUserId()));

        Comment comment = commentMapper.createCommentToEntity(commCreateCommentReqDto);
        return commentRepository.save(comment);
    }

    /**
     * 댓글 수정
     *
     * @param commupdateCommentReqDto 댓글 수정 요청 DTO
     * @return 수정된 댓글 엔티티
     */
    @Override
    public Comment updateComment(CommUpdateCommentReqDto commupdateCommentReqDto) {
        Comment comment = commentRepository.findById(commupdateCommentReqDto.getCommentId())
                .orElseThrow(() -> new CommentException("Comment not found with id: " + commupdateCommentReqDto.getCommentId()));

        userRepository.findById(commupdateCommentReqDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + commupdateCommentReqDto.getUserId()));

        comment = commentMapper.updateCommentToEntity(comment, commupdateCommentReqDto);
        return commentRepository.save(comment);
    }

    /**
     * 댓글 삭제
     *
     * @param commDeleteCommentReqDto 댓글 삭제 요청 DTO
     * @return 삭제 처리된 댓글 엔티티
     */
    @Override
    public Comment deleteComment(CommDeleteCommentReqDto commDeleteCommentReqDto) {
        Comment comment = commentRepository.findById(commDeleteCommentReqDto.getCommentId())
                .orElseThrow(() -> new CommentException("Comment not found with id: " + commDeleteCommentReqDto.getCommentId()));

        userRepository.findById(commDeleteCommentReqDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + commDeleteCommentReqDto.getUserId()));

        comment = commentMapper.deleteCommentToEntity(comment);
        return commentRepository.save(comment);
    }

}
