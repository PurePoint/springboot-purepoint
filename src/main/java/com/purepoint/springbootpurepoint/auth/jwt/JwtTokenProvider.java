package com.purepoint.springbootpurepoint.auth.jwt;

import com.purepoint.springbootpurepoint.user.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenProvider {

    @Value("${purepoint.jwt.secret}")
    private String secretKey;

    private final long validityInMilliseconds = 3600000; // 1시간

    // JWT 토큰 생성
    public String createToken(UserDto user, String roles) {
        Claims claims = Jwts.claims().setSubject(user.getUserId().toString());
        claims.put("nickname", user.getNickname());
        claims.put("email", user.getEmail());
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims) // 페이로드 클레임 (사용자 정보)
                .setIssuedAt(now) // iat (발급일)
                .setExpiration(validity) // exp (만료시간)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // JWT 토큰에서 사용자 정보 추출 (나중에 수정)
    public String getUserId (String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

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
