package com.purepoint.springbootpurepoint.community.service;

import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.CommunityRequestDto;
import com.purepoint.springbootpurepoint.community.dto.CommunityResponseDto;
import com.purepoint.springbootpurepoint.community.mapper.CommunityMapper;
import com.purepoint.springbootpurepoint.community.repository.CommunityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{

    private final CommunityRepository communityRepository;
    private final CommunityMapper communityMapper = CommunityMapper.INSTANCE;

    @Override
    @Transactional
    public Community createPost(CommunityRequestDto.createPost communityRequestDto) {
        Community community = communityMapper.createPost(communityRequestDto);
        community = communityRepository.save(community);
        return community;
    }

    @Override
    public Community updatePost(CommunityRequestDto.updatePost communityRequestDto) {
        Optional<Community> optionalCommunity = communityRepository.findById(communityRequestDto.getBoardId());

        if(optionalCommunity.isPresent()) {
            Community community = optionalCommunity.get();
            communityMapper.updatePost(communityRequestDto);
            community.setPostUpdateAt(LocalDateTime.now());
            community = communityRepository.save(community);
            return community;

        } else {
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다. ID: " + communityRequestDto.getBoardId());
        }
    }

    @Override
    public CommunityResponseDto getPostById(CommunityRequestDto.getBoardId communityRequestDto) {
        Optional<Community> community = communityRepository.findById(communityRequestDto.getBoardID());
        return communityMapper.getPost(community);
    }

    @Override
    public CommunityResponseDto getLatestPostById(CommunityRequestDto.getBoardId communityRequestDto) {
        Optional<Community> community = communityRepository.findByBoardIdOrderByPostAtDesc(communityRequestDto.getBoardID(), Sort.by("postAt").descending());
        return communityMapper.getPost(community);
    }

    @Override
    public CommunityResponseDto getPopularPostById(CommunityRequestDto.getBoardId communityRequestDto) {
        Optional<Community> community = communityRepository.findByBoardIdOrderByPostViewDesc(communityRequestDto.getBoardID(), Sort.by("postView").descending());
        return communityMapper.getPost(community);
    }

    @Override
    public void deletePost(CommunityRequestDto.getBoardId communityRequestDto) {
        communityRepository.deleteById(communityRequestDto.getBoardID());
    }

}
