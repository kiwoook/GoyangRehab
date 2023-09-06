package com.study.goyangrehab.domain.auth.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
