package com.itmo.evaluationsystem.Model;

import lombok.Data;

@Data
public class LoginInfo {
    private Integer id;
    private String username;
    private Integer role;
    private String token;
}
