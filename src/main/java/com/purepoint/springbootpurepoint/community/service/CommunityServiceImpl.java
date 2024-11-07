package com.purepoint.springbootpurepoint.community.service;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.*;
import com.purepoint.springbootpurepoint.community.mapper.CommentMapper;
import com.purepoint.springbootpurepoint.community.mapper.CommunityMapper;
import com.purepoint.springbootpurepoint.community.repository.CommentRepository;
import com.purepoint.springbootpurepoint.community.repository.CommunityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{

    private final CommunityRepository communityRepository;
    private final CommunityMapper communityMapper = CommunityMapper.INSTANCE;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;

    @Override
    public Community createPost(CommCreatePostReqDto commCreatePostReqDto) {
        Community community = communityMapper.createPostToEntity(commCreatePostReqDto);
        community = communityRepository.save(community);
        return community;
    }

    @Override
    public Community updatePost(CommUpdatePostReqDto commUpdatePostReqDto) {
        Optional<Community> optionalCommunity = communityRepository.findById(commUpdatePostReqDto.getPostId());

        if(optionalCommunity.isPresent()) {
            Community community = optionalCommunity.get();
            communityMapper.updatePostToEntity(commUpdatePostReqDto, community);
            community.setPostUpdateAt(LocalDateTime.now());
            community = communityRepository.save(community);
            return community;

        } else {
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다. ID: " + commUpdatePostReqDto.getPostId());
        }
    }

    @Override
    public List<CommReadPostResDto> getPost() {
        List<Community> community = communityRepository.findAll();
        return communityMapper.toDto(community);
    }

    @Override
    public List<CommReadPostResDto> getLatestPost() {
        List<Community> community = communityRepository.findAll(Sort.by("postAt").descending());
        return communityMapper.toDto(community);
    }

    @Override
    public List<CommReadPostResDto> getPopularPost() {
        List<Community> community = communityRepository.findAll(Sort.by("postView").descending());
        return communityMapper.toDto(community);
    }

    @Override
    public void deletePost(UUID postId) {
        Optional<Community> optionalCommunity = communityRepository.findById(postId);

        if(optionalCommunity.isPresent()) {
            Community community = optionalCommunity.get();
            community.setPostDeleteAt(LocalDateTime.now());
            communityRepository.save(community);
        } else {
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다. ID: " + postId);
        }
    }

    @Override
    public CommDetailPostResDto getDetailPost(UUID postId) {
        Optional<Community> community = communityRepository.findById(postId);
        return communityMapper.detailPostToDto(community);
    }

    @Override
    public List<CommCommentResDto> getCommentsPost(UUID postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return commentMapper.toDto(comments);
    }

}
