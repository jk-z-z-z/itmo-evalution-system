package com.itmo.evaluationsystem.Controller;

import com.itmo.evaluationsystem.Model.LoginInfo;
import com.itmo.evaluationsystem.Model.Result;
import com.itmo.evaluationsystem.Model.dto.auth.LoginRequest;
import com.itmo.evaluationsystem.Service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public Result login(LoginRequest loginRequest) {
        log.info("Login Request");
        LoginInfo loginInfo=loginService.login(loginRequest);
        if(loginInfo!=null){
            return Result.success(loginInfo);
        }
        return Result.error();
    }


}
