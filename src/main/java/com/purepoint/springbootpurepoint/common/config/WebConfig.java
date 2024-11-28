package com.purepoint.springbootpurepoint.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 루트 경로("/")로 접근 시 "/swagger-ui/index.html"로 리디렉트 설정
        registry.addRedirectViewController("/", "/swagger-ui/index.html");
    }

    // `PathMatchConfigurer`를 사용해 모든 요청 경로에 "api/v1" 프리픽스를 추가합니다
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("api/v1", c -> true);
    }

}