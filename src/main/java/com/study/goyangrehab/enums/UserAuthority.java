package com.study.goyangrehab.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserAuthority {
    ADMIN("ADMIN"),
    USER("USER"),
    GUEST("GUEST");

    private final String role;

    @Override
    public String toString() {
        return role;
    }
}
