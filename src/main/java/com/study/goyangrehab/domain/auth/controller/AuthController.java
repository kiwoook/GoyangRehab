package com.study.goyangrehab.domain.auth.controller;

import com.study.goyangrehab.domain.auth.dto.LoginRequestDto;
import com.study.goyangrehab.domain.auth.dto.TokenDto;
import com.study.goyangrehab.domain.auth.filter.JwtFilter;
import com.study.goyangrehab.domain.auth.token.TokenProvider;
import com.study.goyangrehab.enums.UserAuthority;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "auth", description = "인증 API")
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    static final Logger logger = LogManager.getLogger(AuthController.class);

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping()
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginRequestDto loginDto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);
        response.addCookie(cookie);

        return new ResponseEntity<>(new TokenDto(accessToken), httpHeaders, HttpStatus.OK);
    }

    @Operation(summary = "액세스 토큰 재발급", description = "액세스 토큰 만료 시 리프레시 토큰으로 유저 확인 후  액세스 토큰 재발급")
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenDto> refreshToken(@CookieValue(name = "refreshToken", required = true) String refreshToken) {
        if (refreshToken != null && tokenProvider.validateToken(refreshToken)) {
            // refreshToken이 유효한 경우, 새로운 accessToken 생성
            Authentication authentication = tokenProvider.getAuthentication(refreshToken);
            String newAccessToken = tokenProvider.createAccessToken(authentication);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + newAccessToken);
            return new ResponseEntity<>(new TokenDto(newAccessToken), httpHeaders, HttpStatus.OK);
        } else {
            // refreshToken이 유효하지 않은 경우 에러 응답
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // TODO 권한 부여
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{userId}")
    public ResponseEntity<String> grantRole(
            @PathVariable String userId,
            @RequestBody UserAuthority authority) {
        return null;
    }
}
