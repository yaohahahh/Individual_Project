package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.service.AdminService;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class WebController {

    /**
     * 默认请求接口
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success();
    }

    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;

    /*登陆接口*/
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account dbAccount;
        if (account.getRole().equals("ADMIN")){
            dbAccount = adminService.login(account);
        } else if (account.getRole().equals("USER")){
            dbAccount = userService.login(account);
        } else {
            return Result.error(" Wrong Role!");
        }
        return Result.success(dbAccount);
    }

    /*register接口*/
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())){
            return Result.error("Must fill Username and Password!");
        }

        if (account.getRole().equals("ADMIN")){
            adminService.register(account);
            return Result.success();
        } else if (account.getRole().equals("USER")) {
            userService.register(account);
            return Result.success();
        } else {
            return Result.error(" Wrong Role!");
        }
    }
}
