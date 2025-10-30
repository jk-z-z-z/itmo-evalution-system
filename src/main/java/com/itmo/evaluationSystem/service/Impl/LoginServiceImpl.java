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
    public LoginInfo register(LoginRequest loginRequest) {
        // 用户名唯一校验
        Admin exist = adminMapper.selectByUsername(loginRequest.getUsername());
        if (exist != null) {
            return null;
        }

        // 生成BCrypt密码散列并插入
        Admin admin = new Admin();
        admin.setUsername(loginRequest.getUsername());
        admin.setPassword(passwordCryptoUtil.encode(loginRequest.getPassword()));
        admin.setRole(0); // 默认角色，可按需调整
        adminMapper.insert(admin);

        // 生成token并返回登录信息
        java.util.HashMap<String, Object> map = new java.util.HashMap<>();
        map.put("id", admin.getId());
        map.put("username", admin.getUsername());
        String token = JwtUtils.generateJwt(map);

        LoginInfo loginInfo = new LoginInfo();
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
