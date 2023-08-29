package com.study.goyangrehab.domain.admin.service;

import com.study.goyangrehab.domain.user.entity.User;
import com.study.goyangrehab.enums.UserAuthority;

public interface AdminService {
    User getUserById(String userId);
    User getUserByUsername(String username);
    User getUserByEmail(String email);

    User grantAuthority(String userId, UserAuthority userAuthority);

}
