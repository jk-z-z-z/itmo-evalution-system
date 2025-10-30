package com.itmo.evaluationSystem.model;

import lombok.Data;

@Data
public class LoginInfo {
    private Integer id;
    private String username;
    private Integer role;
    private String token;
}
