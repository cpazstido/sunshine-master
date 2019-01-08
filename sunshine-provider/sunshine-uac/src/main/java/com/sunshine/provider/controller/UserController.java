package com.sunshine.provider.controller;

import com.sunshine.provider.model.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    /**
     * 用户信息校验
     * @param authentication 信息
     * @return 用户信息
     */
    @RequestMapping("/user")
    public Object user(Authentication authentication) {
        SecurityUser sysUser = new SecurityUser(1L,"admin","","",1L,"");
        return sysUser;
    }
}
