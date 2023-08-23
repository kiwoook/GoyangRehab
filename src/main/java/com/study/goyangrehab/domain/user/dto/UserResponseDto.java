package com.study.goyangrehab.domain.user.dto;

import com.study.goyangrehab.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {

    String userId;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
    }
}
