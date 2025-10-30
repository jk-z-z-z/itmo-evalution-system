package com.itmo.evaluationSystem.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class PasswordCryptoUtil {

    @Value("${security.password.strength:10}")
    private int strength;

    private volatile BCryptPasswordEncoder encoder;

    private BCryptPasswordEncoder getEncoder() {
        if (encoder == null) {
            synchronized (this) {
                if (encoder == null) {
                    encoder = new BCryptPasswordEncoder(strength);
                }
            }
        }
        return encoder;
    }

    // 不可逆散列：用于存储
    public String encode(String rawPassword) {
        return getEncoder().encode(rawPassword);
    }

    // 校验：原始密码与散列值匹配
    public boolean matches(String rawPassword, String encodedPassword) {
        return getEncoder().matches(rawPassword, encodedPassword);
    }
}