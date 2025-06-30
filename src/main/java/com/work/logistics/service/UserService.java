package com.work.logistics.service;

import com.work.logistics.common.exception.MyException;
import com.work.logistics.entity.Users;
import com.work.logistics.mapper.UserMapper;
import com.work.logistics.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.work.logistics.common.Status;


import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 登录
    public String login(String id, String password) throws MyException {
        Users user = userMapper.selectByUserId(id);
        if (user == null || !JwtUtils.matches(password, user.getPassword())) {
            throw new MyException(Status.LOGIN_FAILED);
        }
        return JwtUtils.generateToken(user.getId(), user.getRole());

    }

    // 注册
    public Users register(Users user) throws MyException {
        // 角色为空时默认user
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("user");
        } else {
            user.setRole(user.getRole().trim());
        }
        // 角色首位数字
        String rolePrefix = "admin".equalsIgnoreCase(user.getRole()) ? "2" : "1";

        // 查询数据库中该角色最大ID
        String maxId = userMapper.selectMaxUserIdByRolePrefix(rolePrefix);

        int nextNum = 1; // 默认起始后5位数字
        if (maxId != null && maxId.length() == 6) {
            // 取后5位数字，转换为int，加1
            String suffix = maxId.substring(1);
            try {
                nextNum = Integer.parseInt(suffix) + 1;
            } catch (NumberFormatException e) {
                throw new MyException("用户ID格式错误");
            }
        }
        // 格式化后5位数字，补0到5位
        String nextNumStr = String.format("%05d", nextNum);

        // 拼接新的用户ID
        String newUserId = rolePrefix + nextNumStr;

        user.setId(newUserId);
        user.setPassword(JwtUtils.encodePassword(user.getPassword()));
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPhone(user.getPhone());

        userMapper.insertUser(user);
        return user;
    }


    // 修改个人信息
    public void updateInfo(String token, Users userInfo) throws MyException{
        String userId = JwtUtils.getUserId(token);
        userInfo.setId(userId);
        userMapper.updateUserInfo(userInfo);
    }

    // 修改密码
    public void updatePassword(String token, String oldPassword, String newPassword) throws MyException{
        String userId = JwtUtils.getUserId(token);

        Users user = userMapper.selectById(userId);
        if (JwtUtils.matches(oldPassword, user.getPassword())) {
            Users updateUser = new Users();
            updateUser.setId(userId);
            updateUser.setPassword(JwtUtils.encodePassword(newPassword));
            userMapper.updatePassword(updateUser);
        }
    }

    // 获取个人信息
    public Users getInfo(String token) throws MyException{
        String userId = JwtUtils.getUserId(token);
        return userMapper.selectById(userId);
    }

    // 获取用户信息
    public Users getUserById(String id) {
    	return userMapper.selectById(id);
    }
}

