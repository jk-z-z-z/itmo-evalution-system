package com.itmo.evaluationsystem.Service;

import com.itmo.evaluationsystem.Model.LoginInfo;
import com.itmo.evaluationsystem.Model.dto.auth.LoginRequest;

public interface LoginService {
    LoginInfo login(LoginRequest loginRequest);

    // 提供散列与匹配方法，便于在注册/重置场景复用
    String encodePassword(String raw);
    boolean matchesPassword(String raw, String encoded);
}
