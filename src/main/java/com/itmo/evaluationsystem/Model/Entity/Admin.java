package com.itmo.evaluationsystem.Model.Entity;

import lombok.Data;

@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private Integer role;
    private String addressIP;
    private String addressPortName;

}
