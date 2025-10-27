package com.itmo.evaluationsystem.Service;

import com.itmo.evaluationsystem.Model.LoginInfo;
import com.itmo.evaluationsystem.Model.dto.auth.LoginRequest;

public interface LoginService {
    LoginInfo login(LoginRequest loginRequest);
}
