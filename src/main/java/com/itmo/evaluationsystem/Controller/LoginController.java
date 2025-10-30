package com.itmo.evaluationsystem.Controller;

import com.itmo.evaluationsystem.Model.LoginInfo;
import com.itmo.evaluationsystem.Model.Result;
import com.itmo.evaluationsystem.Model.dto.auth.LoginRequest;
import com.itmo.evaluationsystem.Service.LoginService;
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
