package com.study.goyangrehab.domain.user.service;

import com.study.goyangrehab.domain.user.dto.UserCreateRequestDto;
import com.study.goyangrehab.domain.user.dto.UserUpdateRequestDto;
import com.study.goyangrehab.domain.user.entity.User;

public interface UserService {
    User create(UserCreateRequestDto userDto);

    User update(String userId, UserUpdateRequestDto userDto);

    User delete(String userId);

    User authenticate(String username, String password);

    boolean isUserIdAvailable(String userId);

    boolean isNicknameAvailable(String nickname);

    boolean isEmailAvailable(String email);

}
