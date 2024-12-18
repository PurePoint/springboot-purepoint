package com.purepoint.springbootpurepoint.community.service;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreateCommentReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommDeletePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommUpdatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommDetailPostResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommPagingResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommReadPostResDto;
import com.purepoint.springbootpurepoint.community.mapper.CommentMapper;
import com.purepoint.springbootpurepoint.community.mapper.CommunityMapper;
import com.purepoint.springbootpurepoint.community.repository.CommentRepository;
import com.purepoint.springbootpurepoint.community.repository.CommunityRepository;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final CommunityMapper communityMapper = CommunityMapper.INSTANCE;
//    private final CommentRepository commentRepository;
//    private final CommentMapper commentMapper = CommentMapper.INSTANCE;

    // 글 작성
    @Override
    public Community createPost(CommCreatePostReqDto commCreatePostReqDto) {
        userRepository.findById(commCreatePostReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Community community = communityMapper.createPostToEntity(commCreatePostReqDto);
        community = communityRepository.save(community);
        return community;
    }

    // 글 수정
    @Override
    public Community updatePost(CommUpdatePostReqDto commUpdatePostReqDto) {
        Optional<Community> optionalCommunity = communityRepository.findById(commUpdatePostReqDto.getPostId());

        userRepository.findById(commUpdatePostReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(optionalCommunity.isPresent()) {
            Community community = optionalCommunity.get();
            community = communityMapper.updatePostToEntity(community, commUpdatePostReqDto);
            return communityRepository.save(community);
        } else {
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다. ID: " + commUpdatePostReqDto.getPostId());
        }
    }

    // 글 조회
    @Override
    public CommPagingResDto getPost(String videoId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Community> community = communityRepository.findAllByVideoIdAndPostDeleteAtIsNull(videoId, pageable);

        List<String> nicknames = community.stream()
                .map(post -> userRepository.findById(post.getUserId())
                        .map(User::getNickname)
                        .orElse("Unknown"))
                .toList();

        List<CommReadPostResDto> commReadPostResDtos = communityMapper.toDto(community.getContent(), nicknames);

        return communityMapper.toCommPagingDto(commReadPostResDtos, community);
    }

    // 글 삭제
    @Override
    public void deletePost(CommDeletePostReqDto commDeletePostReqDto) {
        Optional<Community> optionalCommunity = communityRepository.findById(commDeletePostReqDto.getPostId());

        userRepository.findById(commDeletePostReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(optionalCommunity.isPresent()) {
            Community community = optionalCommunity.get();
            community = communityMapper.deletePostToEntity(community);
            communityRepository.save(community);
        } else {
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다. ID: " + commDeletePostReqDto.getPostId());
        }
    }

    @Override
    public List<CommReadPostResDto> getLatestPost() {
        List<Community> community = communityRepository.findAll(Sort.by(Sort.Order.desc("postAt")));
        return communityMapper.toDto(community);
    }

    @Override
    public List<CommReadPostResDto> getPopularPost() {
        List<Community> community = communityRepository.findAll(Sort.by(Sort.Order.desc("postView")));
        return communityMapper.toDto(community);
    }


    // ToDo 새 댓글 생성 서비스 구현
//    @Override
//    public Comment createComment(CommCreateCommentReqDto commCreateCommentReqDto) {
//        userRepository.findById(commCreateCommentReqDto.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        communityRepository.findById(commCreateCommentReqDto.getPostId())
//                .orElseThrow(() -> new RuntimeException("Post not found"));
//
//        Comment comment = commentMapper.createPostToEntity(commCreateCommentReqDto);
//        return commentRepository.save(comment);
//    }

    // ToDo 댓글 수정 서비스 구현

    // ToDo 댓글 삭제 서비스 구현

}
