package com.purepoint.springbootpurepoint.video.service;

import com.purepoint.springbootpurepoint.video.domain.VideoLike;
import com.purepoint.springbootpurepoint.video.dto.UpdateVideoLikeStatusReqDto;
import com.purepoint.springbootpurepoint.video.dto.VideoDto;
import com.purepoint.springbootpurepoint.video.dto.VideoLikeStatusReqDto;
import com.purepoint.springbootpurepoint.video.dto.VideoLikeStatusResDto;

import java.util.List;

public interface VideoService {
    /**
     * @return 유튜브 영상 데이터 리턴
     */
    List<VideoDto> getYoutubeVideo(String category);

    /**
     * @param query 검색 쿼리
     * @return 검색 결과 리턴
     */
    List<VideoDto> searchYoutubeVideo(String query);

    VideoLikeStatusResDto getVideoLikeStatus(VideoLikeStatusReqDto videoLikeStatusReqDto);

    /**
     * @param updateVideoLikeStatusReqDto videoLike 상태 정보
     * @return 좋아요 수 업데이트 결과 리턴
     */
    VideoLike updateVideoLike(UpdateVideoLikeStatusReqDto updateVideoLikeStatusReqDto);
}