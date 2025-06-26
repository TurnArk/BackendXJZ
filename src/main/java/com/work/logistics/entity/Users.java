package com.work.logistics.entity;

import lombok.Data;
import java.time.LocalDateTime;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;

@Data
public class Users {

    /**
     * 用户账号
     */
    @Id(keyType = KeyType.Generator)
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户角色（user / admin）
     */
    private String role;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;
}
