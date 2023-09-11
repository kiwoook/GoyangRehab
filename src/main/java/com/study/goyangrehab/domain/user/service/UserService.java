package com.study.goyangrehab.domain.user.service;

import com.study.goyangrehab.domain.user.dto.UserCreateRequestDto;
import com.study.goyangrehab.domain.user.dto.UserResponseDto;
import com.study.goyangrehab.domain.user.dto.UserUpdateRequestDto;
import com.study.goyangrehab.domain.user.entity.User;
import org.springframework.security.core.Authentication;

public interface UserService {
    UserResponseDto create(UserCreateRequestDto userDto);

    UserResponseDto update(String userId, UserUpdateRequestDto userDto);

    UserResponseDto delete(String userId);


    boolean isUserIdAvailable(String userId);

    boolean isNicknameAvailable(String nickname);

    boolean isEmailAvailable(String email);

    boolean isOwner(Authentication authentication, String id);

}
