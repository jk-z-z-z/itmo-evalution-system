package com.itmo.evaluationSystem.service;

import com.itmo.evaluationSystem.model.LoginInfo;
import com.itmo.evaluationSystem.model.dto.auth.LoginRequest;

public interface LoginService {
    LoginInfo login(LoginRequest loginRequest);

    // 注册并返回登录信息（自动登录）
    LoginInfo register(LoginRequest loginRequest);

    // 提供散列与匹配方法，便于在注册/重置场景复用
    String encodePassword(String raw);
    boolean matchesPassword(String raw, String encoded);
}
