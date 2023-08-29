package com.study.goyangrehab.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateRequestDto {
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

    public UserUpdateRequestDto(String password, String confirmPassword, String fullName, String nickname, String birthDate, String gender, String phoneNumber, String email, String address, String benefitType, boolean disability, boolean familyRegistration, boolean emailNotification, boolean smsNotification) {
        this.password = password;
        this.confirmPassword = confirmPassword;
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
}


