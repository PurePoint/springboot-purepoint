package com.purepoint.springbootpurepoint.common.jwt;

import com.purepoint.springbootpurepoint.oauth.service.OAuthUserDetailsService;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${purepoint.jwt.secret}")
    private String secretKey;

    private final long validityInMilliseconds = 3600000; // 1시간
    private final long refreshTokenValidityInMilliseconds = 604800000; // 7일

    private final OAuthUserDetailsService userDetailsService;

    // JWT 토큰 생성
    public String createToken(UserDto user, String roles) {
        Claims claims = Jwts.claims().setSubject(user.getUserId().toString());
        claims.put("nickname", user.getNickname());
        claims.put("email", user.getEmail());
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        Date refreshValidity = new Date(now.getTime() + refreshTokenValidityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims) // 페이로드 클레임 (사용자 정보)
                .setIssuedAt(now) // iat (발급일)
                .setExpiration(validity) // exp (만료시간)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // JWT 토큰에서 사용자 아이디 추출
    public UUID getUserIdFromToken (String token) {
        String userid = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return UUID.fromString(userid);
    }

    // JWT 토큰에서 사용자 정보 추출
    public UserDetails getUserDetailsFromToken(String token) {
        UUID userId = getUserIdFromToken(token);
        return userDetailsService.loadUserByUserId(userId);
    }

    // Refresh Token 생성

    // JWT 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("JWT 토큰이 유효하지 않습니다.");
        }
    }
}
