package com.purepoint.springbootpurepoint.community;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class CommunityTest {

    @Test
    @Transactional
    @Rollback
    public void testCreatePost() {

    }
}
