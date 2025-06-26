package com.work.logistics.mapper;

import com.work.logistics.entity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Users selectByUserId(String id);
    int insertUser(Users user);
    int updateUserInfo(Users user);
    int updatePassword(Users user);
    Users selectById(String id);
    String selectMaxUserIdByRolePrefix(String rolePrefix);
}