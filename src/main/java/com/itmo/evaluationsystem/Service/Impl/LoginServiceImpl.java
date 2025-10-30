package com.itmo.evaluationsystem.Service.Impl;

import com.itmo.evaluationsystem.Mapper.AdminMapper;
import com.itmo.evaluationsystem.Model.Entity.Admin;
import com.itmo.evaluationsystem.Model.LoginInfo;
import com.itmo.evaluationsystem.Model.dto.auth.LoginRequest;
import com.itmo.evaluationsystem.Service.LoginService;
import com.itmo.evaluationsystem.Utils.JwtUtils;
import com.itmo.evaluationsystem.Utils.PasswordCryptoUtil;
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
