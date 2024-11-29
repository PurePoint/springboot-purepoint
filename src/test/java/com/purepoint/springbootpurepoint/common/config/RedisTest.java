package com.purepoint.springbootpurepoint.config;

import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import com.purepoint.springbootpurepoint.video.repository.VideoLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.UUID;

@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private VideoLikeRepository videoLikeRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Redis 테스트")
    public void redisTest() {
        final ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();

        stringStringValueOperations.set("key", "value");
        log.info("stringStringValueOperations's key = {}",stringStringValueOperations.get("key"));
    }

    @Test
    @DisplayName("Redis count 테스트")
    public void redisCountTest() {


//        userRepository.save(User.builder()
//                .userId(UUID.randomUUID())
//                .email("test22@test.com")
//                .nickname("test22")
//                .profileImage("testsetst")
//                .build());

//        videoLikeRepository.save(VideoLike.builder()
//                .id("test")
//                .videoId("test")
//                .userId(UUID.randomUUID())
//                .videoLikeStatus("LIKE")
//                .build());

//        log.info("videoLikeRepository.countByVideoId: {}", videoLikeRepository.countVideoLikeByVideoId("-HpnldGdgbY"));
        log.info("videoLikeRepository.count: {}", videoLikeRepository.count());
        log.info("videoLikeRepository.countByVideoId: {}", videoLikeRepository.findAllByVideoId("-HpnldGdgbY").size());
        log.info("videoLikeRepository.findByVideoIdAndUserId: {}", videoLikeRepository.findByVideoIdAndUserId("-HpnldGdgbY", UUID.fromString("343a7d2a-d340-4e36-a8cb-c3785131a2cf")));
    }
}
