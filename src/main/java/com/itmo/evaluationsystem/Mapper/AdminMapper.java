package com.itmo.evaluationsystem.Mapper;

import com.itmo.evaluationsystem.Model.Entity.Admin;
import com.itmo.evaluationsystem.Model.dto.auth.LoginRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select id,username,password,role,address_IP,address_name,name " +
            "from admin where username=#{username} and password=#{password}")
    Admin selectByUsernameAndPassword(LoginRequest loginRequest);

    @Select("select id,username,password,role,address_IP,address_name,name " +
            "from admin where username=#{username}")
    Admin selectByUsername(String username);
}
