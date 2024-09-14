package com.dztzb003.j2t.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 自定义用户表 entity
 *
 * @author j2t
 * @date 2024/09/14 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    /**
     * id primary
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 账户名 unique, not null
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色
     */
    private String roles;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    /**
     * 最近登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 登录 ip
     */
    private String loginIp;

    /**
     * 注册时ip
     */
    private String createIp;


}
