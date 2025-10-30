package com.itmo.evaluationSystem.service.Impl;

import com.itmo.evaluationSystem.mapper.AdminMapper;
import com.itmo.evaluationSystem.model.entity.Admin;
import com.itmo.evaluationSystem.model.LoginInfo;
import com.itmo.evaluationSystem.model.dto.auth.LoginRequest;
import com.itmo.evaluationSystem.service.LoginService;
import com.itmo.evaluationSystem.utils.JwtUtils;
import com.itmo.evaluationSystem.utils.PasswordCryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private PasswordCryptoUtil passwordCryptoUtil;

    @Override
    public LoginInfo login(LoginRequest loginRequest) {
        // 基于用户名查询，使用不可逆散列进行匹配
        Admin admin = adminMapper.selectByUsername(loginRequest.getUsername());
        if (admin == null) {
            return null;
        }
        if (!passwordCryptoUtil.matches(loginRequest.getPassword(), admin.getPassword())) {
            return null;
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", admin.getId());
        map.put("username",admin.getUsername());
        String token= JwtUtils.generateJwt(map);

        LoginInfo loginInfo=new LoginInfo();
        loginInfo.setId(admin.getId());
        loginInfo.setUsername(admin.getUsername());
        loginInfo.setRole(admin.getRole());
        loginInfo.setToken(token);


        return loginInfo;
    }

    @Override
    public String encodePassword(String raw) {
        return passwordCryptoUtil.encode(raw);
    }

    @Override
    public boolean matchesPassword(String raw, String encoded) {
        return passwordCryptoUtil.matches(raw, encoded);
    }
}
