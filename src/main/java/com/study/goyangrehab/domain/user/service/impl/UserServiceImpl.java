package com.study.goyangrehab.domain.user.service.impl;

import com.study.goyangrehab.domain.user.dto.UserCreateRequestDto;
import com.study.goyangrehab.domain.user.dto.UserUpdateRequestDto;
import com.study.goyangrehab.domain.user.entity.User;
import com.study.goyangrehab.domain.user.repository.UserRepository;
import com.study.goyangrehab.domain.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User create(UserCreateRequestDto requestDto) {
        User user = UserCreateRequestDto.toEntity(requestDto);
        return userRepository.save(user);
    }

    @Override
    public User update(String userId, UserUpdateRequestDto userDto) throws EntityNotFoundException {
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new EntityNotFoundException("User not found USER_ID : "+ userId));

        return null;
    }

    @Override
    public User delete(String userId) {
        return null;
    }

    @Override
    public User authenticate(String username, String password) {
        return null;
    }

    @Override
    public boolean isUserIdAvailable(String userId) {
        return false;
    }

    @Override
    public boolean isNicknameAvailable(String nickname) {
        return false;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
