package com.work.logistics.controller;

import com.work.logistics.common.Result;
import com.work.logistics.common.exception.MyException;
import com.work.logistics.entity.Users;
import com.work.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 注册
    @PostMapping("/register")
    public Result<Users> register(@RequestBody Users user) throws MyException{
        Users registeredUser = userService.register(user);
        return Result.success(registeredUser);
    }

    // 登录
    @PostMapping("/login")
    public Result<String> login(@RequestBody Map<String, String> loginData) throws MyException{
        String id = loginData.get("id");
        String password = loginData.get("password");
        String token = userService.login(id, password);
        return Result.success(token);
    }

    // 获取个人信息
    @GetMapping("/info")
    public Result<Users> getInfo(@RequestHeader("Authorization") String token) throws MyException{
        Users user = userService.getInfo(token);
        return Result.success(user);
    }

    // 修改个人信息
    @PutMapping("/info")
    public Result<Void> updateInfo(
            @RequestHeader("Authorization") String token,
            @RequestBody Users userInfo
    ) throws MyException{
        userService.updateInfo(token, userInfo);
        return Result.success();
    }

    // 修改密码
    @PutMapping("/password")
    public Result<Void> updatePassword(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, String> passwordData
    ) throws MyException{
        String oldPassword = passwordData.get("oldPassword");
        String newPassword = passwordData.get("newPassword");
        userService.updatePassword(token, oldPassword, newPassword);
        return Result.success();
    }


}
