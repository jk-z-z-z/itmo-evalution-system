package com.itmo.evaluationSystem.controller;

import com.itmo.evaluationSystem.model.LoginInfo;
import com.itmo.evaluationSystem.model.Result;
import com.itmo.evaluationSystem.model.dto.auth.LoginRequest;
import com.itmo.evaluationSystem.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginRequest loginRequest) {
        log.info("Login Request");
        LoginInfo loginInfo=loginService.login(loginRequest);
        if(loginInfo!=null){
            return Result.success(loginInfo);
        }
        return Result.error();
    }

    // 注册：创建管理员并返回登录信息（自动登录）
    @PostMapping("/register")
    public Result register(@RequestBody LoginRequest loginRequest) {
        log.info("Register Request");
        if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty() ||
                loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            return Result.error("用户名或密码不能为空");
        }
        LoginInfo loginInfo = loginService.register(loginRequest);
        if (loginInfo != null) {
            return Result.success(loginInfo);
        }
        return Result.error("用户名已存在");
    }

    // 密码哈希生成（用于注册或调试）
    @PostMapping("/encode")
    public Result encode(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            return Result.error();
        }
        String encoded = loginService.encodePassword(loginRequest.getPassword());
        return Result.success(encoded);
    }


}
