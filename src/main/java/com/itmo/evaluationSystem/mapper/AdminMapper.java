package com.itmo.evaluationSystem.mapper;

import com.itmo.evaluationSystem.model.entity.Admin;
import com.itmo.evaluationSystem.model.dto.auth.LoginRequest;
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
