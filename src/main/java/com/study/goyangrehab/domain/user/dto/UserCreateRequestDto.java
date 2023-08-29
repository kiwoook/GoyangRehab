package com.study.goyangrehab.domain.user.dto;

import com.study.goyangrehab.domain.user.entity.User;
import com.study.goyangrehab.enums.UserAuthority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequestDto {
    private String userId;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String nickname;
    private String birthDate;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String benefitType; // Benefits: recipient, lowIncome, none
    private boolean disability; // Disabilities: disabled, nonDisabled
    private boolean familyRegistration; // Family Registration: registered, unregistered
    private boolean emailNotification;
    private boolean smsNotification;

    @Builder
    public UserCreateRequestDto(String userId, String password, String confirmPassword, String fullName, String nickname, String birthDate, String gender, String phoneNumber, String email, String address, String benefitType, boolean disability, boolean familyRegistration, boolean emailNotification, boolean smsNotification, PasswordEncoder passwordEncoder) {
        this.userId = userId;
        this.password = passwordEncoder.encode(password);
        this.confirmPassword = passwordEncoder.encode(confirmPassword);
        this.fullName = fullName;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.benefitType = benefitType;
        this.disability = disability;
        this.familyRegistration = familyRegistration;
        this.emailNotification = emailNotification;
        this.smsNotification = smsNotification;
    }

    public static User toEntity(UserCreateRequestDto requestDto){
        return User.builder()
                .userId(requestDto.getUserId())
                .password(requestDto.password)
                .userRole(UserAuthority.ROLE_USER)
                .build();
    }

    public boolean isPasswordConfirmed(){
        return password.equals(confirmPassword);
    }
}
