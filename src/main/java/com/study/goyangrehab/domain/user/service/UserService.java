package com.study.goyangrehab.domain.user.service;

import com.study.goyangrehab.domain.user.entity.User;
import com.study.goyangrehab.dto.UserCreateRequestDto;
import com.study.goyangrehab.dto.UserUpdateRequestDto;
import com.study.goyangrehab.enums.Authority;

public interface UserService {
    User createUser(UserCreateRequestDto userDto);

    User getUserById(String userId);
    User getUserByUsername(String username);
    User getUserByEmail(String email);

    User updateUser(Long userId, UserUpdateRequestDto userDto);

    User deleteUser(String userId);

    User grantAuthority(String userId, Authority authority);

    User authenticate(String username, String password);

    boolean isUsernameAvailable(String username);
    boolean isEmailAvailable(String email);

}
