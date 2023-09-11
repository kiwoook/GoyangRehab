package com.study.goyangrehab.domain.user.service.impl;

import com.study.goyangrehab.domain.user.dto.UserCreateRequestDto;
import com.study.goyangrehab.domain.user.dto.UserResponseDto;
import com.study.goyangrehab.domain.user.dto.UserUpdateRequestDto;
import com.study.goyangrehab.domain.user.entity.User;
import com.study.goyangrehab.domain.user.repository.UserRepository;
import com.study.goyangrehab.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final String USER_FIND_ERROR = "User not found USER_ID : ";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDto create(UserCreateRequestDto requestDto) {
        // 중복검사 구현
        Optional<User> existUser = userRepository.findUserByUserId(requestDto.getUserId());
        if (existUser.isPresent()) {
            throw new IllegalArgumentException("user_id is Already");
        }
        User user = UserCreateRequestDto.toEntity(requestDto, passwordEncoder);
        return new UserResponseDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponseDto update(String userId, UserUpdateRequestDto requestDto) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new UsernameNotFoundException(USER_FIND_ERROR + userId));
        user.update(requestDto);
        return new UserResponseDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponseDto delete(String userId) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new UsernameNotFoundException(USER_FIND_ERROR + userId));
        userRepository.delete(user);
        return new UserResponseDto(userRepository.save(user));
    }


    @Override
    public boolean isUserIdAvailable(String userId) {
        Optional<User> user = userRepository.findUserByUserId(userId);

        return user.isEmpty();
    }

    @Override
    public boolean isNicknameAvailable(String nickname) {
        return !(userRepository.existsByName(nickname));
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return false;
    }

    @Override
    public boolean isOwner(Authentication authentication, String id) {
        String userId = authentication.getName();
        return userId.equals(id);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new UsernameNotFoundException(USER_FIND_ERROR + userId));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

}
