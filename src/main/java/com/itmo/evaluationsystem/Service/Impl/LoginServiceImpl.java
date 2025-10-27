package com.itmo.evaluationsystem.Service.Impl;

import com.itmo.evaluationsystem.Mapper.AdminMapper;
import com.itmo.evaluationsystem.Model.Entity.Admin;
import com.itmo.evaluationsystem.Model.LoginInfo;
import com.itmo.evaluationsystem.Model.dto.auth.LoginRequest;
import com.itmo.evaluationsystem.Service.LoginService;
import com.itmo.evaluationsystem.Utils.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {

    private AdminMapper adminMapper;

    @Override
    public LoginInfo login(LoginRequest loginRequest) {
        Admin admin=adminMapper.selectByUsernameAndPassword(loginRequest);
        if(admin==null){
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
}
